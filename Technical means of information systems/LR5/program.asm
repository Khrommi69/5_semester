.data
chr db 'F'
x1 dw 10  ; col
y1 dw 10  ; row
x2 dw 50
y2 dw 20
color db 6

.code   
begin:
    mov ax, @data
    mov ds, ax
    mov es, ax      
    
    call InputInt
    mov x2, ax
    call InputInt
    mov y2, ax
    
    mov ah, 0       
    mov al, 13h    
    int 10h        
       
    mov cx, x1      
    mov dx, y1     
    mov ah, 0Ch    
                   
    xor bh, bh     
    mov al, color     
    
c1:                 
    int 10h       
    cmp dx, y2     
    jne lp         
    cmp cx, x2      
    jne lp2        
    jmp ex         
lp:
    inc dx          
    jmp c1
lp2:
    inc cx
    jmp c1
     
ex:  
   
c2:  
    int 10h
    cmp dx, y1
    jne lp3
    cmp cx, x1
    jne lp4
    jmp ex2
lp3:
    dec dx
    jmp c2
lp4:
    dec cx
    jmp c2  
ex2:    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h
    int 21h    
 InputInt proc
 
    mov ah,0ah
    xor di,di
    mov dx,offset buff 
    int 21h 
    mov dl,0ah
    mov ah,02
    int 21h 
    
    mov si,offset buff+2
    cmp byte ptr [si],"-" 
    jnz ii1
    mov di,1  
    inc si    
ii1:
    xor ax,ax
    mov bx,10  
ii2:
    mov cl,[si] 
    cmp cl,0dh  
    jz endin
    
    cmp cl,'0' 
    jb er
    cmp cl,'9' 
    ja er
 
    sub cl,'0'
    mul bx    
    add ax,cx 
    inc si    
    jmp ii2    
 
er:
    mov dx, offset error
    mov ah,09
    int 21h
    int 20h

endin:
    cmp di,1 
    jnz ii3
    neg ax  
ii3:
    ret
 
error db "incorrect number$"
buff    db 6,7 Dup(?)
InputInt endp
end begin