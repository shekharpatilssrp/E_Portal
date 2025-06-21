document.addEventListener('DOMContentLoaded', () => {
  const dropdownToggle = document.getElementById('dropdownToggle');
  const dropdownMenu = document.getElementById('dropdownMenu');

  // Toggle dropdown visibility
  dropdownToggle.addEventListener('click', (e) => {
    e.stopPropagation();
    const isVisible = dropdownMenu.style.display === 'block';
    dropdownMenu.style.display = isVisible ? 'none' : 'block';
  });

  // Hide dropdown when clicking outside
  window.addEventListener('click', () => {
    dropdownMenu.style.display = 'none';
  });

  // Show logged-in or logged-out menu
  function updateMenu() {
    const username = localStorage.getItem('username');
    if (username) {
      dropdownToggle.textContent = `Account ▼`;
      dropdownMenu.innerHTML = `
        <a href="#">Hi, ${username}</a>
        <a href="/changepassword">Change Password</a>
        <a href="/logout" id="logout">Logout</a>
      `;
      document.getElementById('logout').addEventListener('click', () => {
        localStorage.removeItem('username');
        updateMenu();
      });
      } else {
      dropdownToggle.textContent = `Login ▼`;
      dropdownMenu.innerHTML = `
        <a href="/login" id="login">Login</a>
        <a href="/register" id="register">Register</a>
      `;
       document.getElementById('loginbtn').addEventListener('click', () => {
        const usernamestored = document.getElementById('username')
        const user = usernamestored.value.toUpperCase();
        localStorage.setItem("username" , user);
        updateMenu();
      });
      document.getElementById('register').addEventListener('click', () => {
      });
    }
  }

  updateMenu();
});