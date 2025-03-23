const namesRegex = /^[a-zA-Z]+$/;
const usernamePasswordRegex = /^[a-zA-Z0-9_]+$/;

let isFirstNameValid = false;
let isLastNameValid = false;
let isUsernameValid = false;
let isPasswordValid = false;
let isPasswordConfirmationValid = false;

// Funzione per validare il nome
function validateFirstName() {
    const firstName = document.getElementById('firstname').value;
    const errorElement = document.getElementById('firstnameError');
    const indicator = document.getElementById('firstnameIndicator');

    if (indicator) {
        indicator.classList.remove('success', 'error');
    }

    if (!namesRegex.test(firstName)) {
        errorElement.textContent = 'First Name must contain only letters.';
        if (indicator) {
            indicator.classList.add('error'); // Rosso per errore
        }
        isFirstNameValid = false;
    } else {
        errorElement.textContent = '';
        if (indicator) {
            indicator.classList.add('success'); // Verde per successo
        }
        isFirstNameValid = true;
    }

    bottonShow(); // Aggiorna lo stato del pulsante
}

// Funzione per validare il cognome
function validateLastName() {
    const lastName = document.getElementById('lastname').value;
    const errorElement = document.getElementById('lastnameError');
    const indicator = document.getElementById('lastnameIndicator');

    if (indicator) {
        indicator.classList.remove('success', 'error');
    }

    if (!namesRegex.test(lastName)) {
        errorElement.textContent = 'Last Name must contain only letters.';
        if (indicator) {
            indicator.classList.add('error'); // Rosso per errore
        }
        isLastNameValid = false;
    } else {
        errorElement.textContent = '';
        if (indicator) {
            indicator.classList.add('success'); // Verde per successo
        }
        isLastNameValid = true;
    }

    bottonShow(); // Aggiorna lo stato del pulsante
}

// Funzione per validare lo username
function validateUsername() {
    const username = document.getElementById('username').value;
    const errorElement = document.getElementById('usernameError');
    const indicator = document.getElementById('usernameIndicator');

    if (indicator) {
        indicator.classList.remove('success', 'error');
    }

    if (!usernamePasswordRegex.test(username)) {
        errorElement.textContent = 'Username must contain only letters, numbers, and underscores.';
        if (indicator) {
            indicator.classList.add('error'); // Rosso per errore
        }
        isUsernameValid = false;
    } else {
        errorElement.textContent = '';
        if (indicator) {
            indicator.classList.add('success'); // Verde per successo
        }
        isUsernameValid = true;
    }

    bottonShow(); // Aggiorna lo stato del pulsante
}

// Funzione per validare la password
function validatePassword() {
    const password = document.getElementById('password').value;
    const errorElement = document.getElementById('passwordError');
    const indicator = document.getElementById('passwordIndicator');

    if (indicator) {
        indicator.classList.remove('success', 'error');
    }

    if (!usernamePasswordRegex.test(password) || password.length < 8 || password.length > 15) {
        errorElement.textContent = 'Password must contain only letters, numbers, underscores, and be 8-15 characters long.';
        if (indicator) {
            indicator.classList.add('error'); // Rosso per errore
        }
        isPasswordValid = false;
    } else {
        errorElement.textContent = '';
        if (indicator) {
            indicator.classList.add('success'); // Verde per successo
        }
        isPasswordValid = true;
    }

    bottonShow(); // Aggiorna lo stato del pulsante
}

// Funzione per validare la conferma della password
function validatePasswordConfirmation() {
    const password = document.getElementById('password').value;
    const passwordCheck = document.getElementById('passwordtwo').value;
    const errorElement = document.getElementById('passwordtwoError');
    const indicator = document.getElementById('passwordtwoIndicator');

    if (indicator) {
        indicator.classList.remove('success', 'error');
    }

    if (passwordCheck !== password) {
        errorElement.textContent = 'Passwords do not match.';
        if (indicator) {
            indicator.classList.add('error'); // Rosso per errore
        }
        isPasswordConfirmationValid = false;
    } else {
        errorElement.textContent = '';
        if (indicator) {
            indicator.classList.add('success'); // Verde per successo
        }
        isPasswordConfirmationValid = true;
    }

    bottonShow(); // Aggiorna lo stato del pulsante
}

// Funzione per aggiornare lo stato del pulsante
function bottonShow() {
    const form = document.getElementById('userForm');
    const existingButton = document.getElementById('submit');

    if (isFirstNameValid &&
        isLastNameValid &&
        isUsernameValid &&
        isPasswordValid &&
        isPasswordConfirmationValid) {
        if (!existingButton) {
            const submitButton = document.createElement('button');
            submitButton.id = 'submit';
            submitButton.type = 'submit';
            submitButton.className = 'button Clickable';
            submitButton.textContent = 'Create Account';
            form.appendChild(submitButton);
        }
    } else {
        if (existingButton) {
            form.removeChild(existingButton);
        }
    }
}

// Listener per la validazione in tempo reale
document.getElementById('firstname').addEventListener('input', validateFirstName);
document.getElementById('lastname').addEventListener('input', validateLastName);
document.getElementById('username').addEventListener('input', validateUsername);
document.getElementById('password').addEventListener('input', validatePassword);
document.getElementById('passwordtwo').addEventListener('input', validatePasswordConfirmation);