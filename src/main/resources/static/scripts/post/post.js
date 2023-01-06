var spin = '<button class="btn btn-outline-danger" type="button" disabled>'+
            '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>'+
            ' Loading...</button>'

$(function(){
    $('#table').html(`
    <div class="text-center">
        ${spin}
    </div>
    `)

    loadPostTable(1, function(text){
        $('#table').replaceWith(text)
    })
})