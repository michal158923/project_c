function goQuiz () {
    const userCode = document.getElementById('userCode').textContent;
    window.location.href = 'http://localhost:8080/quiz?userCode=' + userCode;
}

var elStartQuiz = document.getElementById('startQuiz');
if (elStartQuiz) elStartQuiz.addEventListener('click', goQuiz);

function goMineSweeper () {
    const userCode = document.getElementById('userCode').textContent;
    window.location.href = 'http://localhost:8080/mineSweeper?userCode=' + userCode;
}

var elStartMinesweeper = document.getElementById('startMinesweeper');
if (elStartMinesweeper) elStartMinesweeper.addEventListener('click', goMineSweeper);
