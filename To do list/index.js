let button = document.querySelector("#button");

button.addEventListener("click", ()=>{
    let taskinput = document.querySelector("#task");
    let taskvalue = taskinput.value.trim();

    if(taskvalue === ""){
        return;
    }

    let  tasklist = document.querySelector(".task-list");
    let li = document.createElement("li")
    li.innerHTML = `${taskvalue} <span class="delete" onclick='deletetask(this)'>.         ❌</span>`;
    tasklist.appendChild(li);
    taskinput.value = "";
})

function deletetask(element){
    element.parentElement.remove();
}