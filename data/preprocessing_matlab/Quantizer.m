function quant = Quantizer(x)
    if rem(x,5) == 0
        quant = int8(x/5)*5;
    else
        quant = int8(floor((x+5)/5)*5);
    end
end