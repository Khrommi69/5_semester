org 100h

CALL main
RET

.code
main PROC
    MOV AX, 257                     ;+4
    MOV BX, 0                       ;+4
    MOV CX, -257                    ;+4 
    
    CALL f                          ;+19
    RET
main ENDP

f PROC
    MOV f_c, CX                            ;+10
    SAL AX, 1    ;AX=2*a                   ;+2
    SUB CX, AX   ;CX=c-2*a                 ;+4
    MOV CX, 3    ;move result to AX        ;+2
                 ;AX=AX*CX, where CX=3
    IMUL CX                                ;+4
    SUB BX, f_c  ;b=b-c                    ;+(128-154)
    INC BX       ;b=b+1                    ;+16
    SAR BX, 1    ;b=b/2                    ;+2
    ADD AX, BX                             ;+2
    MOV BX, 0                              ;+3
    MOV CX, 0                              ;+4
                                           ;+4
    RET
f ENDP

.data
    f_c dw ?