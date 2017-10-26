//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

//***IMPORTANTE***
//Todo lo mostrado aqui está incluido en la librería de Adafruit_TFTLCD para TFT LCD Shield 2.4" chip ILI9341 
//Se tomó un ejemplo de la misma librería y lo modificamos para crear lo que queríamos.
//Las librerias que se incluyen y algunas variables y objetos son identicas al código fuente ya que estas se necesitan para que funcione
//las opciones touch y mostrar cosas en la pantalla.

#include <Adafruit_GFX.h>    // Libreria de graficos
#include <Adafruit_TFTLCD.h> // Libreria de LCD
#include <TouchScreen.h>     // Libreria del panel tactil

#define MINPRESSURE 1
#define MAXPRESSURE 1000

#define YP A1 
#define XM A2 
#define YM 7 
#define XP 6 

TouchScreen ts = TouchScreen(XP, YP, XM, YM, 364);

// Pines de conexion del LCD 
#define LCD_CS A3 // Chip Select - Pin analogico 3
#define LCD_CD A2 // Command/Data - Pin Analogico 2
#define LCD_WR A1 // LCD Write - Pin Analogico 1
#define LCD_RD A0 // LCD Read - Pin Analogico 0
#define LCD_RESET A4 // LCD Reset - Pin Analogico 4

Adafruit_TFTLCD tft(LCD_CS, LCD_CD, LCD_WR, LCD_RD, LCD_RESET); // Instancia del LCD 

short TS_MINX = 0; // Coordenadas del panel tactil para delimitar
short TS_MINY = 0; // el tamaño de la zona donde podemos presionar
short TS_MAXX = 1023; // y que coincida con el tamaño del LCD
short TS_MAXY = 1023; 

#define	BLACK    0xFFFF                  
#define	CYAN     0xF800 
#define	MAGENTA  0x07E0 
#define WHITE    0x0000  
#define YELLOW   0x001F 
#define RED      0x07FF
#define BLUE     0xFFE0
#define GREEN    0xF81F

int X; // Variables que almacenaran la coordenada
int Y; // X, Y donde presionemos y la variable Z 
int Z; // almacenara la presion realizada

int opciones = 0;

void setup() 
{
  Serial.begin(9600);
  tft.begin(0x9341); 
  
  pinMode(13, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(4, OUTPUT);
  
  tft.fillScreen(BLACK);
  
  tft.setRotation(3);
    
  tft.setCursor(40, 20); 
  tft.setTextColor(WHITE);           
  tft.setTextSize(6); 
  tft.println("TITANIC");
  tft.drawLine(20, 80, 300, 80, RED);
  
  tft.drawLine(160, 90, 160, 230, WHITE);
  //boton izquierdo
  tft.setCursor(50, 200); 
  tft.setTextColor(WHITE);           
  tft.setTextSize(3); 
  tft.println("Izq");
  tft.fillTriangle(100, 125, 50, 145, 100, 165, WHITE);
  //boton derecho
  tft.setCursor(220, 200); 
  tft.setTextColor(WHITE);           
  tft.setTextSize(3); 
  tft.println("Der");
  tft.fillTriangle(220, 125, 270, 145, 220, 165, WHITE);
  
}

void loop() 
{   
    int contar = 0;
    // la pantalla es de 320 X 240
    lecturaPanel();
    //lado derecho
    if((X > 0 && X < 160) && (Y > 90 && Y < 240) && (Z > MINPRESSURE && Z < MAXPRESSURE)){
      if(contar%3 == 0){
        Serial.println("der");
      }
      contar++;
    }
    //lado izquierdo
    if((X > 160 && X < 340) && (Y > 90 && Y < 240) && (Z > MINPRESSURE && Z < MAXPRESSURE)){ 
      if(contar%3 == 0){
        Serial.println("izq");
      }
      contar++;
    } 
    //boton de titanic
    if((X > 20 && X < 300) && (Y > 0 && Y < 80) && (Z > MINPRESSURE && Z < MAXPRESSURE)){ 
      if(contar%3 == 0){
        Serial.println("tit");
      }
      contar++;
    } 
}

void lecturaPanel()
{
    digitalWrite(13, HIGH); 
    TSPoint p = ts.getPoint(); // Realizamos lectura de las coordenadas
    digitalWrite(13, LOW);
  
    pinMode(XM, OUTPUT); // La librería utiliza estos pines como entrada y salida
    pinMode(YP, OUTPUT); // por lo que es necesario declararlos como salida justo
                         // despues de realizar una lectura de coordenadas.    
  
    // Mapeamos los valores analogicos leidos del panel tactil (0-1023)
    // y los convertimos en valor correspondiente a la medida del LCD 320x240
    X = map(p.x, TS_MAXX, TS_MINX, tft.width(), 0);
    Y = map(p.y, TS_MAXY, TS_MINY, tft.height(), 0);
    Z = p.z;
}

/* TFT Graphic functions

   BLACK   0x0000  // Color codes for background, text or filled circles, squares or triangles.                 
   RED     0xF800 
   GREEN   0x07E0  
   WHITE   0xFFFF  
   BLUE    0x001F 
   CYAN    0x07FF
   YELLOW  0xFFE0
   MAGENTA 0xF81F
 
 - tft.reset();                                     // Reset Screen
 - tft.begin(identifier);                           // Set LCD driver (identifier = 0x9325; 0x9328; 0x7575; 0x9341; 0x8357)
 - tft.width();                                     // Returns lcd width in pixels (240)
 - tft.height();                                    // Returns lcd height in pixels (320)
 - tft.setRotation(A);                              // Rotate screen (A = 0; 1; 2; 3;)
 - tft.fillScreen(color);                           // Set LCD Background color (See colour codes a few lines up)
 - tft.setCursor(X, Y);                             // Set cursor on lcd X, Y coordinates in pixels
 - tft.setTextSize(A);                              // Set text size (A = 1; 2; 3; 4; .... 100? )
 - tft.setTextColor(color);                         // Set text colour (see colour codes a few lines up)
 - tft.print("text");                               // Write on LCD some "text"
 - tft.println("text");                             // Write on LCD some "text" and set cursor on a new line
 - tft.drawLine(Xs, Ys, Xf, Yf, color);             // Draw a line (Start X coordinate, start Y coordinate, end X coordinate, end Y coordinate, color)
 - tft.drawRect(Xs, Ys, width, height, color);      // Draw a square/rectangle (Start X coordinate, start Y coordinate, Square width, square height, color)
 - tft.fillRect(Xs, Ys, width, height, color);      // Draw a filled square/rectangle (Start x coordinate, start Y coordinate, Square width, square height, color)
 - tft.drawCircle(Xs, Ys, Radius, color);           // Draw a circle (Start X coordinate, Start Y coordinate, circle radius, color)
 - tft.fillCircle(Xs, Ys, Radius, color);           // Draw a filled circle (Start X coordinate, Start Y coordinate, circle radius, color)
 - tft.drawTriangle(Xs, Ys, Xl, Yl, Xr, Yr, color); // Draw a triangle (Top vertex X coordinate, Top vertex Y coordinate, Bottom left vertex X coordinate, 
                                                                        Bottom left vertex Y coordinate, Bottom right vertex X coordinate, Bottom right vertex Y coordinate, color)
 - tft.fillTriangle(Xs, Ys, Xl, Yl, Xr, Yr, color); // Draw a filled triangle (Top vertex X coordinate, Top vertex Y coordinate, Bottom left vertex X coordinate, 
                                                                              Bottom left vertex Y coordinate, Bottom right vertex X coordinate, Bottom right vertex Y coordinate, color)
 - tft.drawRoundRect(20, 245, 130, 60, 15, RED);    // Draw a round corners square (Start X coordinate, start Y coordinate, Square width, square height, corners radius, color)
 - tft.fillRoundRect(40, 260, 90, 30, 10, GREEN);   // Draw a filled round corners square (Start X coordinate, start Y coordinate, Square width, square height, corners radius, color)
*/
