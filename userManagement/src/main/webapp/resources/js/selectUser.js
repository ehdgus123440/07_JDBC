const updateBtn = document.querySelector("#updateBtn");
const deleteBtn = document.querySelector("#deleteBtn");
const goToList = document.querySelector("#goToList");

goToList.addEventListener("click", () => {
  location.href ="/selectAll"; // 목록 페이지 요청
});

updateBtn.addEventListener("click", () => {
  location.href ="/selectAll"; 
});

deleteBtn.addEventListener("click", () => {

  // confirm을 이용해서 삭제할지 확인
  if( !confirm("삭제 하시겠습니까?")){ // 취소 클릭시
    return;
  }

  // form태그, input태그 생성 후 body 제일 아래에 submit하기
  const deleteForm = document.createElement("form");
  deleteForm.action ="/deleteUser";
  deleteForm.method ="POST";


  const input = document.createElement("input");
  deleteForm.append(input);
  input.type ="hidden";
  input.name ="userNo";

  const userNoTo = document.querySelector("#userNoTd");
  input.value = userNoTo.innerText;

  // body 태그 마지막에 추가
  document.querySelector("body").append(deleteForm);

  deleteForm.submit();

});