package main.java.backend;

public class FramePrice {

    private double BEAM = 3; //брус
    private double step = 1.5; //шаг поперечин
    private double widthBanner; //получаем максимальную сторону
    private double heightBanner; //получаем минимальную сторону
    private double priceOfBeams = 820; // цена брусса

    private int sumBeam; //сумма брусков
    private double beamConsumption; //использовано брусков
    private double framePrice; //цена за каркас
    private double remainderHeightTrim;  //обрезок высоты
    private double quantityRemainderBeamHeight; //количество обрезков высоты
    private double numberCrossbars;  //количество поперечин
    private double needBeamCrossbars; //нужно бруса на поперечину
    private double remainderWidthTrim;  //обрезок ширины
    private double quantityRemainderBeamWidth; //количество обрезков ширины
    private double widthBeam;  // количество бруса на макс сторону1
    private double remainderBeamWidth; //обрезок от ширины
    private double widthBeam2; //количество бруса на макс сторону2

    public FramePrice() {
    }

    public FramePrice(double width, double height) {
        this.widthBanner = Math.max(width, height);
        this.heightBanner = (Math.min(width,height)) - 0.1;
    }

    private void resetAll(){
        beamConsumption = framePrice = remainderHeightTrim = quantityRemainderBeamHeight =
        numberCrossbars = needBeamCrossbars = remainderWidthTrim = quantityRemainderBeamWidth =
        widthBeam =remainderBeamWidth = widthBeam2 = 0;
    }

    public int getNumberOfBeams(){
        resetAll();
        widthBeam = Math.ceil(widthBanner / BEAM);
        remainderBeamWidth = widthBeam * BEAM - widthBanner;
        widthBeam2 = Math.ceil(widthBanner / BEAM);

        if (remainderBeamWidth >= (BEAM - remainderBeamWidth) && remainderBeamWidth != 0 ){
            remainderBeamWidth -= (BEAM - remainderBeamWidth);
            widthBeam2 -= 1;
            quantityRemainderBeamWidth = 1;
        } else {quantityRemainderBeamWidth = 2;}

        numberCrossbars = Math.round((widthBanner / step) - 1);
        numberCrossbars += 2;

        //расчет каркаса до 3х метров по одной стороне
        if (heightBanner <= BEAM){
            while (quantityRemainderBeamWidth !=0 && remainderBeamWidth > heightBanner){
                quantityRemainderBeamWidth--;
                remainderWidthTrim = remainderBeamWidth;
                while (remainderWidthTrim > heightBanner && numberCrossbars !=0){
                    remainderWidthTrim -= heightBanner;
                    numberCrossbars--;
                }
            }
            while (numberCrossbars !=0){
                remainderHeightTrim = BEAM - heightBanner;
                numberCrossbars--;
                beamConsumption++;
                while (numberCrossbars !=0 && remainderHeightTrim > heightBanner){
                    remainderHeightTrim -= heightBanner;
                    numberCrossbars--;
                }
            }

        }

        //расчет каркаса cвыше 3х метров по двум сторонам
        if (heightBanner > BEAM){
            needBeamCrossbars = heightBanner;
            while (needBeamCrossbars > BEAM){
                needBeamCrossbars -= BEAM;
                beamConsumption++;}

            beamConsumption *= numberCrossbars;

            quantityRemainderBeamHeight = numberCrossbars;

            while (quantityRemainderBeamWidth != 0 && remainderBeamWidth >= needBeamCrossbars && needBeamCrossbars !=0){
                quantityRemainderBeamWidth--;
                remainderWidthTrim = remainderBeamWidth;
                while (quantityRemainderBeamHeight != 0 && remainderWidthTrim > needBeamCrossbars){
                    remainderWidthTrim -= needBeamCrossbars;
                    quantityRemainderBeamHeight--;
                }

            }
            while (quantityRemainderBeamHeight != 0){
                remainderHeightTrim = BEAM - needBeamCrossbars;
                quantityRemainderBeamHeight--;
                beamConsumption++;
                while (quantityRemainderBeamHeight != 0 && remainderHeightTrim > needBeamCrossbars){
                    remainderHeightTrim -= needBeamCrossbars;
                    quantityRemainderBeamHeight--;
                }


            }

        }
        sumBeam = (int) (widthBeam + widthBeam2 + beamConsumption);
        return sumBeam;
    }

    public int getNumberOfBeamsSealing(){
        resetAll();
        widthBeam = Math.ceil(widthBanner / BEAM);
        remainderBeamWidth = widthBeam * BEAM - widthBanner;
        widthBeam2 = Math.ceil(widthBanner / BEAM);

        if (remainderBeamWidth >= (BEAM - remainderBeamWidth) && remainderBeamWidth != 0 ){
            remainderBeamWidth -= (BEAM - remainderBeamWidth);
            widthBeam2 -= 1;
            quantityRemainderBeamWidth = 1;
        } else {quantityRemainderBeamWidth = 2;}

        numberCrossbars += 2;

        //расчет каркаса до 3х метров по одной стороне
        if (heightBanner <= BEAM){
            while (quantityRemainderBeamWidth !=0 && remainderBeamWidth > heightBanner){
                quantityRemainderBeamWidth--;
                remainderWidthTrim = remainderBeamWidth;
                while (remainderWidthTrim > heightBanner && numberCrossbars !=0){
                    remainderWidthTrim -= heightBanner;
                    numberCrossbars--;
                }
            }
            while (numberCrossbars !=0){
                remainderHeightTrim = BEAM - heightBanner;
                numberCrossbars--;
                beamConsumption++;
                while (numberCrossbars !=0 && remainderHeightTrim > heightBanner){
                    remainderHeightTrim -= heightBanner;
                    numberCrossbars--;
                }
            }

        }

        //расчет каркаса cвыше 3х метров по двум сторонам
        if (heightBanner > BEAM){
            needBeamCrossbars = heightBanner;
            while (needBeamCrossbars > BEAM){
                needBeamCrossbars -= BEAM;
                beamConsumption++;}

            beamConsumption *= numberCrossbars;

            quantityRemainderBeamHeight = numberCrossbars;

            while (quantityRemainderBeamWidth != 0 && remainderBeamWidth >= needBeamCrossbars && needBeamCrossbars !=0){
                quantityRemainderBeamWidth--;
                remainderWidthTrim = remainderBeamWidth;
                while (quantityRemainderBeamHeight != 0 && remainderWidthTrim > needBeamCrossbars){
                    remainderWidthTrim -= needBeamCrossbars;
                    quantityRemainderBeamHeight--;
                }

            }
            while (quantityRemainderBeamHeight != 0){
                remainderHeightTrim = BEAM - needBeamCrossbars;
                quantityRemainderBeamHeight--;
                beamConsumption++;
                while (quantityRemainderBeamHeight != 0 && remainderHeightTrim > needBeamCrossbars){
                    remainderHeightTrim -= needBeamCrossbars;
                    quantityRemainderBeamHeight--;
                }


            }

        }
        sumBeam = (int) (widthBeam + widthBeam2 + beamConsumption);
        return sumBeam;
    }



    public double getPriceOfBeamsSealing(){
        if (widthBanner <= 1.5 && heightBanner <= 1.5){
            framePrice = priceOfBeams * 2;
            return framePrice;
        } else {
            return getNumberOfBeamsSealing() * 1600;
        }
    }



    public double getFramePrice(){
        framePrice = getNumberOfBeams() * 1600;
        if (framePrice < (7_000 + (getNumberOfBeams() * priceOfBeams))){
            framePrice = 7_000 + (getNumberOfBeams() * priceOfBeams);
        }return framePrice;
    }


}
