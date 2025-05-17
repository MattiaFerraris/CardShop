const namesRegex = /^[a-zA-Z\s'-]{2,50}$/;
const usernamePasswordRegex = /^(?=.*[a-zA-Z0-9])[a-zA-Z0-9_]{3,30}$/;

const validationRules = [
    {
        id: 'firstname',
        errorId: 'firstnameError',
        validate: value => namesRegex.test(value),
        errorMessage: 'First Name must contain only letters, spaces, hyphens, or apostrophes (2-50 characters).'
    },
    {
        id: 'lastname',
        errorId: 'lastnameError',
        validate: value => namesRegex.test(value),
        errorMessage: 'Last Name must contain only letters, spaces, hyphens, or apostrophes (2-50 characters).'
    },
    {
        id: 'username',
        errorId: 'usernameError',
        validate: value => usernamePasswordRegex.test(value),
        errorMessage: 'Username must contain letters, numbers, or underscores (3-30 characters).'
    },
    {
        id: 'password',
        errorId: 'passwordError',
        validate: value => usernamePasswordRegex.test(value) &&
            value.length >= 8 &&
            value.length <= 15 &&
            /[A-Z]/.test(value) &&
            /[a-z]/.test(value) &&
            /[0-9]/.test(value),
        errorMessage: 'Password must be 8-15 characters, with at least one uppercase, lowercase, number, and only letters, numbers, underscores.'
    },
    {
        id: 'passwordtwo',
        errorId: 'passwordtwoError',
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
    const value = input.value;

    if (!input || !errorElement) {
        console.warn(`Field or error element not found for ${field.id}`);
        return;
    }

    if (!touchedFields[field.id]) {
        errorElement.textContent = '';
        input.classList.remove('is-valid', 'is-invalid');
        input.setAttribute('aria-invalid', 'false');
        input.removeAttribute('aria-describedby');
        validationState[field.id] = false;
        updateSubmitButton();
        return;
    }

    if (!value) {
        errorElement.textContent = 'This field is required.';
        input.classList.add('is-invalid');
        input.setAttribute('aria-invalid', 'true');
        input.setAttribute('aria-describedby', field.errorId);
        validationState[field.id] = false;
        updateSubmitButton();
        return;
    }

    input.classList.remove('is-valid', 'is-invalid');

    if (field.validate(value)) {
        errorElement.textContent = '';
        input.classList.add('is-valid');
        input.setAttribute('aria-invalid', 'false');
        input.removeAttribute('aria-describedby');
        validationState[field.id] = true;
    } else {
        errorElement.textContent = field.errorMessage;
        input.classList.add('is-invalid');
        input.setAttribute('aria-invalid', 'true');
        input.setAttribute('aria-describedby', field.errorId);
        validationState[field.id] = false;
    }

    updateSubmitButton();
}

function updateSubmitButton() {
    const submitButton = document.getElementById('submit');
    const isFormValid = Object.values(validationState).every(Boolean) &&
        Object.values(touchedFields).every(Boolean);

    if (submitButton) {
        submitButton.disabled = !isFormValid;
        submitButton.classList.toggle('disabled', !isFormValid);
    } else {
        console.warn('Submit button not found.');
    }
}

document.addEventListener('DOMContentLoaded', () => {
    validationRules.forEach(field => {
        const input = document.getElementById(field.id);
        if (input) {
            input.addEventListener('input', () => {
                touchedFields[field.id] = true;
                validateField(field);
                // Re-validate passwordtwo if password changes
                if (field.id === 'password' && touchedFields['passwordtwo']) {
                    const passwordTwoField = validationRules.find(rule => rule.id === 'passwordtwo');
                    validateField(passwordTwoField);
                }
            });
            input.addEventListener('blur', () => {
                touchedFields[field.id] = true;
                validateField(field);
            });
        } else {
            console.warn(`Input element not found for ${field.id}`);
        }
    });

    const form = document.getElementById('userForm');
    if (form) {
        form.addEventListener('submit', (event) => {
            const isFormValid = Object.values(validationState).every(Boolean);
            if (!isFormValid) {
                event.preventDefault();
                alert('Please fix all errors before submitting.');
            }
        });
    } else {
        console.warn('Form element with id="userForm" not found.');
    }
});