var spin = '<button class="btn btn-outline-danger" type="button" disabled>'+
            '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>'+
            ' Loading...</button>'

$(function(){
    $('#table>#spinner').html(spin)
    loadPostTable(function(response){
        $('#table').html(response)
    })
})