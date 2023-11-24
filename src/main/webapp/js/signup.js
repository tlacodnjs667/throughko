document.addEventListener("DOMContentLoaded", () => {
  const submitBtn = document.querySelector("#submitBtn");
  const inputUserId = document.querySelector("#inputUserId");
  const inputPassword = document.querySelector("#inputPassword");
  const inputNickname = document.querySelector("#inputNickname");
  const inputEmail = document.querySelector("#inputEmail");

  submitBtn.disabled = true;

  let isIdInputValid = false;
  let isPasswordInputValid = false;
  let isNicknameInputValid = false;
  let isEmailInputValid = false;

  inputUserId.addEventListener("keyup", () => {
    isIdInputValid = inputUserId.value.length >= 6;
    onKeyUpToCheck();
  })
  inputPassword.addEventListener("keyup", () => {
    isPasswordInputValid = inputPassword.value.length >= 8;
    onKeyUpToCheck();
  })
  inputEmail.addEventListener("keyup", () => {
    isEmailInputValid = inputEmail.value.length >= 5;
    onKeyUpToCheck();
  })

  inputNickname.addEventListener("keyup", e => {
    isNicknameInputValid = inputNickname.value.length >= 3
        && inputNickname.value.length <= 10;
    onKeyUpToCheck();
  })

  function onKeyUpToCheck() {
    console.log(isIdInputValid);
    console.log(isPasswordInputValid);
    console.log(isEmailInputValid);
    console.log(isNicknameInputValid);
    if (isIdInputValid && isPasswordInputValid && isEmailInputValid
        && isNicknameInputValid) {
      submitBtn.disabled = false;
    }
  }

  const signUpException = document.getElementById("signUpException");
  let opacityValue = 1; // Initial opacity value

  const intervalId = setInterval(() => {
    opacityValue -= 0.05;
    signUpException.style.opacity = opacityValue;

    if (opacityValue <= 0) {
      clearInterval(intervalId); // Stop the interval when opacity reaches 0
    }
  }, 100);
})
