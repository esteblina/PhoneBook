$(document).ready(function(){
$("#register").click(function() {
var login = $("#login").val();
var fullname = $("#fullname").val();
var password = $("#password").val();
var cpassword = $("#cpassword").val();
console.log('sdaffasdfsadfasdfas')
if (login == '' || fullname == '' || password == '' || cpassword == '') {
alert("Please fill all fields!");
} else if ((password.length)<5) {
alert("Password should at least 5 character in length!");
} else if (!(password).match(cpassword)) {
alert("Your passwords don't match. Try again?");
} else if (!/(?=.*\d)(?=.*[A-Z])[0-9a-zA-Z]/.test(password)){
alert("Your password should contain at least one number and one capital letter!");
} else if ((login.length) < 3){
alert("Login should at least 3 character in length!");
} else if (!/^[a-zA-Z]*$/.test(login)){
alert("Your login should contain only Latin characters!");
} else if ((fullname.length) < 5){
alert("Full name should at least 5 character in length!");
} else {
 $('#new-user').submit();
}
});
});