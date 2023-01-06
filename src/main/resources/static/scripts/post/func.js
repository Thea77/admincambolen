    const loadPostTable = (pageNum, callback) => {
    fetch(`/post/tmpl/data?pageNum=${pageNum}`, {
        method: "GET"
    })
    .then(response => response.text())
    .then(text =>{
        // console.log("data"+text)
        if(typeof callback == 'function'){
            callback(text)
        }
    })
}

