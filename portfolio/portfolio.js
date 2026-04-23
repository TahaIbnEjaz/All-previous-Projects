let menuSlide = document.querySelector('.nav-links');

function toggleMenu(){
    if(menuSlide.style.display == "none"){

        menuSlide.style.display = "block";

    }
    else{
        menuSlide.style.display = "none";
    }
}

window.addEventListener('resize', () => {
    if(window.innerWidth > 800){ 
        menuSlide.style.display = "";
    }
});

// #006961