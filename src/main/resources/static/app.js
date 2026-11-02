// Handle form submit (Enter or button click)
    const form = document.getElementById("search-form");

    form.addEventListener("submit", async (event) => {
        // Prevent page reload
        event.preventDefault();

        // User input
        const city = document.getElementById("city-input").value;

        // Cache the error and result elements
        const errorBox = document.getElementById("error");
        const resultBox = document.getElementById("result");

        // Call the backend (encode city for the URL)
        const response = await fetch(`/weather?city=${encodeURIComponent(city)}`);

        // Show error if the city was not found
        if (!response.ok) {
            errorBox.textContent = "H πόλη δεν βρέθηκε";
            errorBox.classList.remove("hidden");
            resultBox.classList.add("hidden");
            return;
        }

        // Parse JSON response
        const data = await response.json();

        // Shortcut for hourly data
        const h = data.hourly;

        // Index of the current hour
        const i = findCurrentHourIndex(h.time);

        // Fill in the weather details
        document.getElementById("city-name").textContent = city;
        document.getElementById("temperature").textContent = `${h.temperature_2m[i]} °C`;
        document.getElementById("wind").textContent = `Άνεμος: ${h.wind_speed_10m[i]} kn από ${h.wind_direction_10m[i]}°`;
        document.getElementById("humidity").textContent = `Υγρασία: ${h.relative_humidity_2m[i]}%`;
        document.getElementById("rain").textContent = `Βροχή: ${h.rain[i]} mm`;

        // Hide error, show result
        errorBox.classList.add("hidden");
        resultBox.classList.remove("hidden");
    });

    // Find the index of the current hour in the times list (e.g. "2026-10-30T18:00")
    function findCurrentHourIndex(times) {
        const now = new Date();
        // Zero-pad to two digits
        const pad = (n) => String(n).padStart(2, "0");
        const prefix = `${now.getFullYear()}-${pad(now.getMonth() + 1)}-${pad(now.getDate())}T${pad(now.getHours())}`;
        const index = times.findIndex((t) => t.startsWith(prefix));
        // Fall back to the first entry if not found
        return index === -1 ? 0 : index;
    }
