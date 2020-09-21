function quant = Quantizer(x)
    if rem(x,3) == 0
        quant = int8(x);
    else
        quant = int8(floor((x+3)/3)*3);
    end
end