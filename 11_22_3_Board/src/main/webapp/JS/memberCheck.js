//	<input>을 넣었을 때 아무것도 안 썼으면 true, 뭐라도 썼으면 false 
function isEmpty(input) {
	return (!input.value);
}

//	<input> 글자수 넣었을 때 그 글자수보다 적으면 true, 그 글자수 이상이면 false
function atLeastLetter(input, len) {
	return (input.value.length < len);
}

//	<input> 넣었을 때 한글 / 특문 / 숫자 적혀있으면 true, 없다면 false
function containsAnother(input) {
	let pass = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXDYZ";
	let iv = input.value;
	for (let i = 0; i < iv.length; i++) {
		if (pass.indexOf(iv[i]) == -1) {
			return true;
		}
	} return false;
}

// <input> x 2 넣었을 때 (pw,pw확인 등) 내용이 다르면 true , 같으면 false
function notEqualPw(input1, input2) {
	return (input1.value !== input2.value);
}

// <input> 문자열 세트 넣었을 때 없으면 true, 있으면 false(입력한 pw에 지정한 문자열이 없는 경우)
function notContains(input, passSet) {
	let iv = input.value;
	for (let i = 0; i < passSet.length; i++) {
		if (iv.indexOf(passSet[i]) !== -1) {
			return false;
		}
	} return true;
}

// <input> 넣었을 때 숫자 아닌게 있으면 true, 숫자만 있으면 false
function isNotNumber(input) {
	return isNaN(input.value);
}

// <input> 확장자 넣었을 때 확장자명 아니면 true, 아니면 false
function isNotType(input, type) {
	// .gif, .png, ...
	type = "." + type;
	return (input.value.indexOf(type) == -1);
}