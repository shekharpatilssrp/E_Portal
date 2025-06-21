document.getElementById("registrationForm").addEventListener("submit", function (e) {
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const termsChecked = document.getElementById("terms").checked;
    const errorMessage = document.getElementById("error-message");

    // Clear previous error
    errorMessage.textContent = "";

    // Validate passwords match
    if (password !== confirmPassword) {
        e.preventDefault();
        errorMessage.textContent = "Passwords do not match.";
        return;
    }

    // Validate terms checkbox
    if (!termsChecked) {
        e.preventDefault();
        errorMessage.textContent = "You must accept the Terms and Conditions.";
        return;
    }

    // Allow form submission
    alert("Form submitted successfully!");
});
