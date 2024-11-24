
var ordineForm = "ordine-form";

function prepareOrdine(){
   // if no item

   return

}

function addCarello(id, nome, prezzo){
    if(existsIdInTable(id)){
       return;
    }
    addRow(id, nome, prezzo);
}

function removeRow(id) {
    return function () {
        let ordineRef = document.getElementById(ordineForm);
        if(ordineRef.children.length < 2) return;
        for(let i = 0 ; i < ordineRef.children.length ; i++){
            if(ordineRef.children[i].id == id){
                ordineRef.children[i].remove();
                return;
            }
        }
        return;
    }
}

function existsIdInTable(id) {
  let ordineRef = document.getElementById(ordineForm);
  for(let i = 0 ; i < ordineRef.children.length ; i++){
    if(ordineRef.children[i].id == id){
        return true;
    }
  }
  return false;
}

function addRow(id, nome, prezzo) {
  // Get a reference to the table
  let ordineRef = document.getElementById(ordineForm);
  if(ordineRef == null) return;
  // Insert a row at the end of the table
  let newRow = document.createElement("div");
  newRow.classList.add("row");
  newRow.classList.add("my-2");
  newRow.id = id;
  let col1 = createDivCol(nome, true, "col-5");
  //let col2 = createDivCol(descrizione, true);
  let col3 = createDivCol(prezzo, true, "col-2");

  let quantitaInput = document.createElement("input");
  quantitaInput.type = "number";
  quantitaInput.classList.add("form-control");
  quantitaInput.value = 1;
  quantitaInput.min = 1;
  quantitaInput.name = id;
  let col4 = createDivCol(quantitaInput, false, "col-3");

  let removeButton =  document.createElement("button");
  removeButton.onclick = removeRow(id)
  //let textButton = document.createTextNode("Cancella");
  let i =  document.createElement("i");
  i.classList.add("fas");
  i.classList.add("fa-trash");
  removeButton.appendChild(i);
  removeButton.classList.add("btn")
  removeButton.classList.add("btn-danger");
  //removeButton.appendChild(textButton);
  let col5 = createDivCol(removeButton, false, "col-2");

  newRow.appendChild(col1);
//  newRow.appendChild(col2);
  newRow.appendChild(col3);
  newRow.appendChild(col4);
  newRow.appendChild(col5);

  ordineRef.appendChild(newRow);
}

function createDivCol(text, isText, style="col-2"){
  let div =  document.createElement("div");
  let textEl ;
  if(isText){
    textEl = document.createTextNode(text);
  }else{
    textEl = text;
  }
  div.classList.add(style);
  div.appendChild(textEl);
  return div;
}
