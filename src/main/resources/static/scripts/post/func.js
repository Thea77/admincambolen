
    const loadPostTable = (callback) => {
    fetch('/tmpl/post/table', {
        method: "GET"
    })
    .then(response => response.text())
    .then(response =>{
        // console.log("data"+response)
        if(typeof callback == 'function'){
            callback(response)
        }
    })
}

