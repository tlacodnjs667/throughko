
document.addEventListener("DOMContentLoaded", () => {
  const resultDesc = document.getElementById("resultDesc");
  const resultDiv = document.getElementById("resultDiv");

  const findIdBtn = document.getElementById("findIdBtn");
  const findPwBtn = document.getElementById("findPwBtn");

  findIdBtn.addEventListener("click", (e) => {
    const emailInputForId = document.getElementById("emailInputForId");

    e.preventDefault();
    const type = document.getElementById('typeForId').value;

    const data = {email: emailInputForId.value, type};
    console.log(data)

    axios.post("./idquery", data, {
      header: {
        "Content-type": "application/x-www-form-urlencoded"
      }
    }).then((res) => {
      console.dir(res.data);

      console.log(typeof res.data);

      if (res.data == "false") {
        alert("해당하는 계정이 없습니다.")
      }
    });
  })

  findPwBtn.addEventListener("click", (e) => {
    const emailInputForPw = document.getElementById("emailInputForPw");
    const idInputForPw = document.getElementById("userIdInputForPw");

    e.preventDefault();
    const type = +(document.getElementById('typeForPw').value);
    const data = {email: emailInputForPw.value, userId: idInputForPw.value, type};

    axios.post(location.href, data)
    .then((res) => {
      console.dir(res.data);

      console.log(typeof res.data);

      if (res.data === false) {
        alert("해당하는 계정이 없습니다.")
      } else {

      }
    });
    emailInputForPw.value = "";
    idInputForPw.value = "";
  });

  async function showCommentModal(e) {

  }


})