$(document).on('click', '.page-link', function(e){
             
    let pageNum = $(e.currentTarget).data('page')

    $('#table').html(`
    <div class="text-center">
        ${spin}
    </div>
    `)

    loadPostTable(pageNum, function(text){
        $('#table').replaceWith(text)
    })
})