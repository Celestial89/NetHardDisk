function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}
function automove() {
  var spiclist = document.getElementById("spiclist");
  for (var i=0; i<pics.length; i++) {
    if (from == 0) {
      changeback(from);
      changes[2].setAttribute("src", "images/2s.jpg");
      moveElement("list",-600,0,5);
      from = 1;
      break;
    }
    if (from == 1) {
      changeback(from);
      changes[3].setAttribute("src", "images/3s.jpg");
      moveElement("list",-1200,0,5);
      from = 2;
      break;
    }
    if (from == 2) {
      changeback(from);
      changes[4].setAttribute("src", "images/4s.jpg");
      moveElement("list",-1800,0,5);
      from = 3;
      break;
    }
    if (from == 3) {
      changeback(from);
      changes[1].setAttribute("src", "images/1s.jpg");
      moveElement("list",0,0,5);
      from = 0;
      break;
    }
  }
  movement = setTimeout("automove()",3000);    
}
function changeback(from) {
      if (from == 0) {
        changes[1].setAttribute("src", "images/1h.jpg");
      }
      if (from == 1) {
        changes[2].setAttribute("src", "images/2h.jpg");
      }
      if (from == 2) {
        changes[3].setAttribute("src", "images/3h.jpg");
      }
      if (from == 3) {
        changes[4].setAttribute("src", "images/4h.jpg");
      }
}
function moveElement(elementID,final_x,final_y,interval) {
  var elem = document.getElementById(elementID);
  if (elem.movement) {
    clearTimeout(elem.movement);
  }
  if (!elem.style.left) {
    elem.style.left = "0px";
  }
  if (!elem.style.top) {
    elem.style.top = "0px";
  }
  var xpos = parseInt(elem.style.left);
  var ypos = parseInt(elem.style.top);
  if (xpos == final_x && ypos == final_y) {
    return true;
  }
  if (xpos < final_x) {
    var dist = Math.ceil((final_x - xpos)/7);
    xpos = xpos + dist;
  }
  if (xpos > final_x) {
    var dist = Math.ceil((xpos - final_x)/7);
    xpos = xpos - dist;
  }
  if (ypos < final_y) {
    var dist = Math.ceil((final_y - ypos)/7);
    ypos = ypos + dist;
  }
  if (ypos > final_y) {
    var dist = Math.ceil((ypos - final_y)/7);
    ypos = ypos - dist;
  }
  elem.style.left = xpos + "px";
  elem.style.top = ypos + "px";
  var repeat = "moveElement('"+elementID+"',"+final_x+","+final_y+","+interval+")";
  elem.movement = setTimeout(repeat,interval);
}
function prepareSlideshow() {
  var list = document.getElementById("list");
  list.style.position = "absolute";
  list.style.top = "0px";
  list.style.left = "0px";
  changes = document.getElementsByTagName("img");
  var spiclist = document.getElementById("spiclist");
  pics = spiclist.getElementsByTagName("a");
  from = 0;
  for (var i=0; i<pics.length; i++) {
    pics[i].onmouseover = function() {
      var destination = this.getAttribute("href");
      if (destination.indexOf("1") != -1) {
        clearTimeout(movement);
        changeback(from);
        changes[1].setAttribute("src", "images/1s.jpg");
        moveElement("list",0,0,5);
        from = 0;
      }
      if (destination.indexOf("2") != -1) {
        clearTimeout(movement);
        changeback(from);
        changes[2].setAttribute("src", "images/2s.jpg");
        moveElement("list",-600,0,5);
        from = 1;
      }
      if (destination.indexOf("3") != -1) {
        clearTimeout(movement);
        changeback(from);
        changes[3].setAttribute("src", "images/3s.jpg");
        moveElement("list",-1200,0,5);
        from = 2;
      }
      if (destination.indexOf("4") != -1) {
        clearTimeout(movement);
        changeback(from);
        changes[4].setAttribute("src", "images/4s.jpg");
        moveElement("list",-1800,0,5);
        from = 3;
      }
    }
    pics[i].onmouseout = function() {
        movement = setTimeout("automove()",3000);
    }
  }
  movement = setTimeout("automove()",3000);
}
addLoadEvent(prepareSlideshow);