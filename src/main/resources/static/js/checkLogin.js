const namesRegex = /^[a-zA-Z]+$/;
const usernamePasswordRegex = /^[a-zA-Z0-9_]+$/;

const validationRules = [
    {
        id: 'firstname',
        errorId: 'firstnameError',
        indicatorId: 'firstnameIndicator',
        validate: value => namesRegex.test(value),
        errorMessage: 'First Name must contain only letters.'
    },
    {
        id: 'lastname',
        errorId: 'lastnameError',
        indicatorId: 'lastnameIndicator',
        validate: value => namesRegex.test(value),
        errorMessage: 'Last Name must contain only letters.'
    },
    {
        id: 'username',
        errorId: 'usernameError',
        indicatorId: 'usernameIndicator',
        validate: value => usernamePasswordRegex.test(value),
        errorMessage: 'Username must contain only letters, numbers, and underscores.'
    },
    {
        id: 'password',
        errorId: 'passwordError',
        indicatorId: 'passwordIndicator',
        validate: value => usernamePasswordRegex.test(value) && value.length >= 8 && value.length <= 15,
        errorMessage: 'Password must contain only letters, numbers, underscores, and be 8-15 characters long.'
    },
    {
        id: 'passwordtwo',
        errorId: 'passwordtwoError',
        indicatorId: 'passwordtwoIndicator',
        validate: value => value === document.getElementById('password').value,
        errorMessage: 'Passwords do not match.'
    }
];

let validationState = {
    firstname: false,
    lastname: false,
    username: false,
    password: false,
    passwordtwo: false
};

let touchedFields = {
    firstname: false,
    lastname: false,
    username: false,
    password: false,
    passwordtwo: false
};

function validateField(field) {
    const input = document.getElementById(field.id);
    const errorElement = document.getElementById(field.errorId);
    const indicator = document.getElementById(field.indicatorId);
    const value = input.value;

    // Non validare se il campo non è stato toccato o è vuoto
    if (!touchedFields[field.id] || !value) {
        errorElement.textContent = '';
        if (indicator) {
            indicator.classList.remove('is-valid', 'is-invalid');
        }
        validationState[field.id] = false;
        updateSubmitButton();
        return;
    }

    // Rimuovi gli stili precedenti
    if (indicator) {
        indicator.classList.remove('is-valid', 'is-invalid');
    }

    // Validazione del campo
    if (field.validate(value)) {
        errorElement.textContent = '';
        if (indicator) {
            indicator.classList.add('is-valid');
        }
        validationState[field.id] = true;
    } else {
        errorElement.textContent = field.errorMessage;
        if (indicator) {
            indicator.classList.add('is-invalid');
        }
        validationState[field.id] = false;
    }

    updateSubmitButton();
}

function updateSubmitButton() {
    const submitButton = document.getElementById('submit');
    const isFormValid = Object.values(validationState).every(state => state) &&
        Object.values(touchedFields).every(touched => touched);

    if (submitButton) {
        submitButton.disabled = !isFormValid;
        submitButton.classList.toggle('disabled', !isFormValid);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    validationRules.forEach(field => {
        const input = document.getElementById(field.id);
        if (input) {
            // Valida solo dopo che l'utente inizia a scrivere
            input.addEventListener('input', () => {
                touchedFields[field.id] = true;
                validateField(field);
            });
            // Valida anche quando il campo perde il focus
            input.addEventListener('blur', () => {
                touchedFields[field.id] = true;
                validateField(field);
            });
        }
    });

    const form = document.getElementById('userForm');
    if (form) {
        form.addEventListener('submit', (event) => {
            const isFormValid = Object.values(validationState).every(state => state);
            if (!isFormValid) {
                event.preventDefault();
                alert('Please fix all errors before submitting.');
            }
        });
    }
});