// JavaScript Document

autoHeight();
setCurrentClick();
imgMouseEvent();
  var elements=$(".popMenu .title");
  for(var k=0;k<elements.length;k++){
     elements[k].onmousedown=function(){
       MouseDown(this);
	 }
	 elements[k].onmouseup=function(){
       mouseUp();
	 }
	 elements[k].onmousemove=function(){
       DragDiv(this);
	 }
	 elements[k].onmouseover=function(){
       mouseOver(this);
	 }
	 elements[k].parentNode.parentNode.onclick=function(){
	   this.style.zIndex=zIndex;
	   zIndex=zIndex+1;
	 }
  }
