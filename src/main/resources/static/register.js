
const sendHttpRequest = (method, url, data) => {
    const promise = new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open(method, url);
        xhr.responseType = 'json'
        if (data){
            xhr.setRequestHeader('Content-Type', 'application/json');
        }
        xhr.onload = () =>{
            resolve(xhr.response);
        }
        xhr.send(JSON.stringify(data));
    });
    return promise;
}
var userCode;
function sendRegister () {
        sendHttpRequest('POST', 'http://localhost:8080/register/user',
            {
                name: document.getElementById('name').value,
                password: document.getElementById('password').value
            })
        .then(responseData => {
            document.getElementById('info').innerHTML = responseData.info;
            if (responseData.info == "new user created successfully"){
                document.getElementById('toContinue').innerHTML = 'account created, press to continues';
                document.getElementById('toContinue').removeAttribute("hidden");
            }
            userCode = responseData.userCode;
        });
};

var elRegister = document.getElementById('register');
if (elRegister) elRegister.addEventListener('click', sendRegister);

function goHome4users () {
    window.location.href = 'http://localhost:8080/homeForUser?userCode=' + userCode;}

var elToContinue = document.getElementById('toContinue');
if (elToContinue) elToContinue.addEventListener('click', goHome4users);



