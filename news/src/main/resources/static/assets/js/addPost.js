function changeFontSize(size) {
    document.getElementById('editor').style.fontSize = size;
  }
  
  function changeFontColor(color) {
    document.getElementById('editor').style.color = color;
  }
  
  function changeFontFamily(font) {
    document.getElementById('editor').style.fontFamily = font;
  }
  
  document.getElementById('fileInput').addEventListener('change', function(event) {
    var file = event.target.files[0];
    if (!file) return;

    var reader = new FileReader();
    reader.onload = function() {
        var arrayBuffer = reader.result;
        var mammothOptions = {
            styleMap: [
                "p[style-name='Section Title'] => h1",
                "p[style-name='Subsection Title'] => h2"
            ]
        };
        mammoth.extractRawText({ arrayBuffer: arrayBuffer }, mammothOptions)
            .then(function(result) {
                document.getElementById('editor').value = result.value;
            })
            .catch(function(err) {
                console.error('Error reading .docx file:', err);
            });
    };
    reader.readAsArrayBuffer(file);
});
document.getElementById('postForm').addEventListener('submit', function(event) {
  event.preventDefault(); // Ngăn form gửi dữ liệu mặc định
  var content = document.getElementById('editor').value;
  var processedContent = content.replace(/\n/g, '[newline]'); // Thay thế các ký tự xuống dòng bằng [newline]
  document.getElementById('editor').value = processedContent; // Gán nội dung đã xử lý lại vào textarea
  this.submit(); // Gửi form
});

  