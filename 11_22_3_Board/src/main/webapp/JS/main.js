/** 1. 전부 빈칸 X
	2. id는 영어만
	3. pw와 pwChk는 같아야 함.
	4. 사진은 png, jpg, gif만 가능.
 * 
 */
function goHome(){
	
	location.href = "HomeController";
	
}
 
function goSignUp() {

	location.href = "SignUpController";

}

function checkSignUp() {

	let m_id = document.signUpForm.m_id;
	let m_pw = document.signUpForm.m_pw;
	let m_pw_check = document.signUpForm.m_pw_check;
	let m_name = document.signUpForm.m_name;
	let m_phone = document.signUpForm.m_phone;
	let m_photo = document.signUpForm.m_photo;

	if (isEmpty(m_id)) {
		m_id.value = "";
		m_id.focus();
		alert('id를 입력해주세요!');
		return false;
	} else if (containsAnother(m_id)) {
		m_id.value = "";
		m_id.focus();
		alert('id는 영문만 입력해주세요!');
		return false;
	} else if (isEmpty(m_pw)) {
		m_pw.value = "";
		m_pw.focus();
		alert('password를 입력해주세요!');
		return false;
	} else if (isEmpty(m_pw_check)) {
		m_pw_check.value = "";
		m_pw_check.focus();
		alert('password 확인을 입력해주세요!');
		return false;
	} else if(notEqualPw(m_pw,m_pw_check)){
		m_pw_check.value = "";
		m_pw_check.focus();
		alert('password와 password 확인이 맞지 않습니다!');
		return false;
	} else if (isEmpty(m_name)){
		m_name.value = "";
		m_name.focus();
		alert('이름을 입력해주세요!');
		return false;
	} else if (isEmpty(m_phone)){
		m_phone.value = "";
		m_phone.focus();
		alert('전화번호를 입력해주세요!');
		return false;
	}  else if (isEmpty(m_photo)){
		m_photo.value = "";
		m_photo.focus();
		alert('사진을 등록해주세요!');
		return false;
	} else if(isNotType(m_photo,'png') && isNotType(m_photo,'jpg') && isNotType(m_photo,'gif')){
		m_photo.value = "";
		m_photo.focus();
		alert('사진 파일의 확장자는 png,jpg,gif만 등록 가능합니다!');
		return false;
	}
	
	return true;
	
}

function checkUpdate() {

	let m_pw = document.updateForm.m_pw;
	let m_pw_check = document.updateForm.m_pw_check;
	let m_name = document.updateForm.m_name;
	let m_phone = document.updateForm.m_phone;
	let m_photo = document.updateForm.m_photo;
	
	if (isEmpty(m_photo)){
		m_photo.value = "";
		m_photo.focus();
		alert('사진을 등록해주세요!');
		return false;
	} else if(isNotType(m_photo,'png') && isNotType(m_photo,'jpg') && isNotType(m_photo,'gif')){
		m_photo.value = "";
		m_photo.focus();
		alert('사진 파일의 확장자는 png,jpg,gif만 등록 가능합니다!');
		return false;
	}
	
	if (isEmpty(m_pw)) {
		m_pw.value = "";
		m_pw.focus();
		alert('password를 입력해주세요!');
		return false;
	} else if (isEmpty(m_pw_check)) {
		m_pw_check.value = "";
		m_pw_check.focus();
		alert('password 확인을 입력해주세요!');
		return false;
	} else if(notEqualPw(m_pw,m_pw_check)){
		m_pw_check.value = "";
		m_pw_check.focus();
		alert('password와 password 확인이 맞지 않습니다!');
		return false;
	} else if (isEmpty(m_name)){
		m_name.value = "";
		m_name.focus();
		alert('이름을 입력해주세요!');
		return false;
	} else if (isEmpty(m_phone)){
		m_phone.value = "";
		m_phone.focus();
		alert('전화번호를 입력해주세요!');
		return false;
	} 	
	
	return true;
	
}

function loginCheck(){
	
	let l_id = document.loginform.l_id;
	let l_pw = document.loginform.l_pw;
	
	if (isEmpty(l_id)) {
		l_id.value = "";
		l_id.focus();
		alert('id를 입력해주세요!');
		return false;
	} else if (containsAnother(l_id)) {
		l_id.value = "";
		l_id.focus();
		alert('id는 영문만 입력해주세요!');
		return false;
	} else if (isEmpty(l_pw)) {
		l_pw.value = "";
		l_pw.focus();
		alert('password를 입력해주세요!');
		return false;
	}
	
	return true;
	
}

function logOut(){
	
	let b = confirm('로그아웃 하시겠습니까?');
	
	if(b){
		
		location.href="loginController";
		
	}
	
}

function myInfo(){
	
	location.href="MemberInfoController";
	
}

function infoDelete(){
	
	let t = prompt('정말로 탈퇴하시겠습니까?(Y/N)');
	
	if(t=='Y' || t=='y'){
		
		location.href="MemberDeleteController";
		
	}
	
	
}

function goInsert(){
	location.href = "BoardWriteController";
}

function goBoard(){
	location.href = "BoardController";
	
}

function updateBtn(b_no){
	
	location.href = "BoardUpdateController?b_no="+b_no;
	
}

function deleteBtn(b_no){
	let t= confirm('진짜로 삭제하시겠어요?');
	if(t){
		location.href = "BoardDeleteController?b_no="+b_no;
	}
	
}

function goSearch(){
	
	location.href = "BoardSearchController";
	
}