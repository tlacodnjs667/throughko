document.addEventListener("DOMContentLoaded", () => {
  const submitBtn = document.querySelector("#submitBtn");

  const inputUserId = document.querySelector("#inputUserId");
  const inputPassword = document.querySelector("#inputPassword");

  submitBtn.disabled = true;

  let isIdInputValid = false;
  let isPasswordInputValid = false;

  inputUserId.addEventListener("keyup", () => {
    isIdInputValid = inputUserId.value.length >= 6;
    onKeyUpToCheck();
  });

  inputPassword.addEventListener("keyup", () => {
    isPasswordInputValid = inputPassword.value.length >= 8;
    onKeyUpToCheck();
  });

  function onKeyUpToCheck() {
    console.log(isIdInputValid);
    console.log(isPasswordInputValid);
    if (isIdInputValid && isPasswordInputValid) {
      submitBtn.disabled = false;
    }
  }

  const signInException = document.getElementById("signInException");
  let opacityValue = 1; // Initial opacity value

  const intervalId = setInterval(() => {
    opacityValue -= 0.05;
    signInException.style.opacity = opacityValue;

    if (opacityValue <= 0) {
      clearInterval(intervalId); // Stop the interval when opacity reaches 0
    }
  }, 100);
})
