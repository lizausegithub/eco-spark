const slider = document.querySelector(".slider");
const figures = Array.from(slider.querySelectorAll("figure"));

let index = 0;
let direction = 1;

function updateSlide() {
  const offset = -index * 100;
  figures.forEach((fig) => (fig.style.transform = `translateX(${offset}%)`));
}

function slideNext() {
  index += 1;

  if (index >= figures.length) index = 0; // Loop back to the first
  updateSlide();
}

function slidePrev() {
  index -= 1;
  if (index < 0) index = figures.length - 1; // Loop back to the last
  updateSlide();
}

function autoSlide() {
  index += direction;

  if (index >= figures.length - 1) {
    direction = -1; // Reverse direction
  } else if (index <= 0) {
    direction = 1; // Forward direction
  }

  if (index > figures.length - 1) {
    index = figures.length - 2;
  } else if (index < 0) {
    index = 1;
  }

  updateSlide();
}

// Attach event listeners for manual navigation
document.getElementById("slider-nextBtn").addEventListener("click", slideNext);
document.getElementById("slider-prevBtn").addEventListener("click", slidePrev);

setInterval(autoSlide, 5000);
