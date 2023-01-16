
$(document).on('click', '.btn-update', function (e) {

  let dataId = $(this).data("id")
  let dataStatus = $(this).data("status");

  console.log("radiovValue="+dataStatus)
  $('#statusId').val(dataId)
 
  if(dataStatus == true){
     $('#active').attr('checked', true);
     $('#active').attr('disabled', 'disabled');
     $('#txtStatus').append(dataStatus);
     $('#txtStatus').addClass("text-info");

  }else{
    $('#disabled').attr('checked', true);
    $('#disabled').attr('disabled', 'disabled');
    $('#txtStatus').append(dataStatus);
    $('#txtStatus').addClass("text-danger");
  }
});



