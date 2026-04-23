let boxes = document.querySelectorAll(".box");
let resetBtn = document.querySelector("#reset");
let newGame = document.querySelector("#new-btn");
let msgContainer = document.querySelector(".msg-container");
let msg = document.querySelector("#msg");
let clickcount = 0;

let turnO = true;  // player X , player O

//wining patterns
const winPatterns =[
    [0,1,2],
    [0,3,6],
    [0,4,8],
    [1,4,7],
    [2,5,8],
    [2,4,6],
    [3,4,5],
    [6,7,8]
];

const resetGame = () =>{
    clickcount = 0;
    turnO = true;
    enableboxes();
    msgContainer.classList.add("hide") 
}
const enableboxes =() =>{
    for(box of boxes){
        box.disabled = false;
        box.innerText = "";
    }
}

boxes.forEach((box) => {
    box.addEventListener("click",()=>{
        if(turnO)
        {
            box.style.color = "#09814A"
            box.innerText = "O"
            turnO = false;
        }
        else
        {
            box.style.color = "#C1666B"
            box.innerText = "X";
            turnO = true;
        }
        box.disabled = true;

        clickcount++;

        checking();
    });
});

const disableboxes =() =>{
    for(box of boxes){
        box.disabled = true;
    }
}

const showWinner = (winner) => {
        msg.innerText =`\"Congratulations , Winner is ${winner} 🎉\"`;
        msgContainer.classList.remove("hide");
        disableboxes();
}

const checking = () => {
    for(pattern of winPatterns)
    {
        let pos1 = boxes[pattern[0]].innerText;
        let pos2 = boxes[pattern[1]].innerText;
        let pos3 = boxes[pattern[2]].innerText;

        if(pos1 != "" && pos2 != "" && pos3 != "")
        {
            if(pos1===pos2 && pos2 === pos3)
            {
                console.log("Winner",pos1);
                showWinner(pos1);
                return;
            }
        }
    }
    if(clickcount === 9)
    {
        msg.innerText="\"Game is Drawn\""
        msgContainer.classList.remove("hide");
        disableboxes();
    }
    
}

newGame.addEventListener("click",resetGame);
resetBtn.addEventListener("click",resetGame);