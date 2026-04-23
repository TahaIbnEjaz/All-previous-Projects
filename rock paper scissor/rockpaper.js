let userscore = 0;
let compscore = 0;

let choices = document.querySelectorAll(".choice");
let msg = document.querySelector("#msg")

let userscorepara = document.querySelector("#user");
let compscorepara = document.querySelector("#comp");

const compchoice = () => {
    const options = ["rock","paper","scissor"];
    const idx = Math.floor(Math.random() * 3);
    return options[idx];
}

const drawgame = () => {
    msg.innerText = "Game Draw"
    msg.style.backgroundColor ="#171215"

}
const showWinner = (userwin,userchoice,Compchoice) =>{
    if(userwin){
        userscore++;
        userscorepara.innerText = userscore;
        msg.innerText = `You Win! Your ${userchoice} beats ${Compchoice}`;
        msg.style.backgroundColor ="green"
    }
    else{
        compscore++;
        compscorepara.innerText = compscore;
        msg.innerText = `You lost.  ${Compchoice} beats ${userchoice}`;
        msg.style.backgroundColor = "red"
    }
}

const playGame = (userchoice) => {
        const Compchoice = compchoice();

        if(userchoice === Compchoice){
            drawgame();
        }
        else{
            let userwin = true;
            if(userchoice === "rock"){
                userwin = Compchoice === "paper" ? false : true;
            }
            else if(userchoice === "paper"){
                userwin = Compchoice === "scissor" ? false : true;
            }
            else{
                userwin = Compchoice === "rock" ? false : true;
            }

            showWinner(userwin,userchoice,Compchoice);
        }
}

choices.forEach((choice) => {
    choice.addEventListener("click", () =>{
        const userchoice = choice.getAttribute("id");
        playGame(userchoice);
    })
})