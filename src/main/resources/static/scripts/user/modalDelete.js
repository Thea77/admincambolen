
   $(".btn-delete").click(function() {
    let dataId = $(this).data("id")
    let href = $('#confirmDelete').attr("href")
    // console.log("ID="+href)
    $('#confirmDelete').attr('href', href + dataId)
  });



