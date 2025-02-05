window.addEventListener('scroll', function () {
    var navbar = this.document.querySelector(".navbar");
    var navbarOffset = navbar.offsetTop;

    if (this.window.scrollY >= navbarOffset) {
        navbar.classList.add("sticky-nav");
    }
    if (window.scrollY <= navbarOffset) {
        navbar.classList.remove("sticky-nav");
    }
});