
var ordineForm = "ordine-form";

function prepareOrdine(){
    let formRef = document.getElementById(ordineForm);

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
  newRow.id = id;
  let col1 = createDivCol(nome, true);
  //let col2 = createDivCol(descrizione, true);
  let col3 = createDivCol(prezzo, true);

  let quantitaInput = document.createElement("input");
  quantitaInput.type = "number";
  quantitaInput.classList.add("form-control");
  quantitaInput.value = 2;
  quantitaInput.min = 1;
  quantitaInput.name = id;
  let col4 = createDivCol(quantitaInput, false);

  let removeButton =  document.createElement("button");
  removeButton.onclick = removeRow(id)
  let textButton = document.createTextNode("Cancella");
  removeButton.classList.add("btn")
  removeButton.classList.add("btn-danger");
  removeButton.appendChild(textButton);
  let col5 = createDivCol(removeButton, false);

  newRow.appendChild(col1);
//  newRow.appendChild(col2);
  newRow.appendChild(col3);
  newRow.appendChild(col4);
  newRow.appendChild(col5);

  ordineRef.appendChild(newRow);
}

function createDivCol(text, isText){
  let div =  document.createElement("div");
  let textEl ;
  if(isText){
    textEl = document.createTextNode(text);
  }else{
    textEl = text;
  }
  div.classList.add("col-2");
  div.appendChild(textEl);
  return div;
}
