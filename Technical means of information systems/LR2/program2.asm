org 100h
jmp start
;Data------------------------------------------------------------------
v dw 12345
pak db 13,10,'Press any key...$'
;----------------------------------------------------------------------
start:
mov bx,[v]    
mov ah,2          
mov cx,16         
lp:
shl bx,1          
mov dl,'0'        
jnc print         
inc dl            
print:
int 21h           
loop lp           
mov ah,9          
mov dx,offset pak 
int 21h             
mov ah,8            
int 21h             
mov ax,4C00h        
int 21h