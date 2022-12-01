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

function sendLogs () {
    sendHttpRequest('POST', 'http://localhost:8080/login/user',
    {
        name: document.getElementById('loginLog').value,
        password: document.getElementById('passwordLog').value
    })
    .then(responseData => {
       if (responseData.info == "logged successfully"){
           window.location.href = 'http://localhost:8080/homeForUser?userCode=' + responseData.userCode;
           
       }else{
        document.getElementById('infoLog').innerHTML = responseData.info;
       }
   });
};

var elsubmitLog = document.getElementById('submitLog');
if (elsubmitLog) elsubmitLog.addEventListener('click', sendLogs);
