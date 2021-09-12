let password = null;
let passwordConfirm = null;
window.onload = () => {
    password = document.getElementById("password");
    passwordConfirm = document.getElementById("password-confirm");
    passwordConfirm.onkeyup = validate;
};

function validate() {
    if (password.value !== passwordConfirm.value) {
        passwordConfirm.setCustomValidity("Passwords Don't Match");
    }else{
        passwordConfirm.setCustomValidity("");
    }
}
