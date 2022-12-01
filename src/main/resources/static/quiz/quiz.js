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
var data;
function getData  ()  {
    const userCode = document.getElementById('userCode').textContent;
    sendHttpRequest('GET', 'http://localhost:8080/quiz/next?userCode=' + userCode).then(responseData => {
        data = responseData
        if (data.questionCount !=  'quiz has been finished') {
            document.getElementById('questionCounter').innerHTML = 'pytanie: ' +  data.questionCount;
            document.getElementById('questionContent').innerHTML = data.question.questionContent;
            document.getElementById('btnAnswer1').innerHTML = data.answers[0].answerContent;
            document.getElementById('btnAnswer2').innerHTML = data.answers[1].answerContent;
            document.getElementById('btnAnswer3').innerHTML = data.answers[2].answerContent;
            document.getElementById('btnAnswer4').innerHTML = data.answers[3].answerContent;
            console.log(data);
        } else {
            window.location.href = 'http://localhost:8080/quiz/end?userCode=' + userCode
        }


    });
};
getData();

function checkAnswer (givenAnswerId) {
    const userCode = document.getElementById('userCode').textContent
    sendHttpRequest('POST', 'http://localhost:8080/quiz/checkAnswer?userCode=' + userCode,
        {
            questionId: data.question.id,
            givenAnswerId: givenAnswerId
        })
    .then(responseData => {
        document.getElementById('btnAnswer1').disabled = true;
        document.getElementById('btnAnswer2').disabled = true;
        document.getElementById('btnAnswer3').disabled = true;
        document.getElementById('btnAnswer4').disabled = true;
        document.getElementById('info').innerHTML = responseData.info;
        document.getElementById('toContinue').removeAttribute('hidden');
        console.log(responseData);
    });
};



function sendAnswer1 () {checkAnswer(data.answers[0].id);}
function sendAnswer2 () {checkAnswer(data.answers[1].id);}
function sendAnswer3 () {checkAnswer(data.answers[2].id);}
function sendAnswer4 () {checkAnswer(data.answers[3].id);}

document.getElementById('btnAnswer1').addEventListener('click', sendAnswer1);
document.getElementById('btnAnswer2').addEventListener('click', sendAnswer2);
document.getElementById('btnAnswer3').addEventListener('click', sendAnswer3);
document.getElementById('btnAnswer4').addEventListener('click', sendAnswer4);

function goQuiz () {
    const userCode = document.getElementById('userCode').textContent;
        window.location.href = 'http://localhost:8080/quiz?userCode=' + userCode;

}

var elToContinue = document.getElementById('toContinue');
if (elToContinue) elToContinue.addEventListener('click', goQuiz);



