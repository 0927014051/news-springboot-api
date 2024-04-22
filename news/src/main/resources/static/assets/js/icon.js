document.addEventListener("DOMContentLoaded", function() {
  const socialLinks = document.querySelectorAll(".social-links li a");

  socialLinks.forEach(function(link) {
    link.addEventListener("mouseenter", function() {
      const arrow = this.querySelector(".arrow");
      arrow.style.opacity = "1";
    });

    link.addEventListener("mouseleave", function() {
      const arrow = this.querySelector(".arrow");
      arrow.style.opacity = "0";
    });
  });
});
