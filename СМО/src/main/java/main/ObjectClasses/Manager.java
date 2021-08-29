package main.ObjectClasses;

import main.Main;

import java.util.ArrayList;

//Выбор заявки из буфера на прибор, выбор прибора по кольцу
public class Manager {
     private static int lastDevice = 0; //номер последнего прибора, который проверили в предыдущий раз
                                        // и с которого начнётся новая проверка

     public void toDevice(ArrayList<Buffer> buffers, ArrayList<Device> devices,
                          ArrayList<Source.Request> requestList, ArrayList<Source> sources) {
          boolean isEmptyAnyone = false; //Проверяем, есть ли свободный прибор

          //Т.К. выборка по кольцу, то смотрим c последнего, проверенного в прошлый раз прибора, до конца массива
          for (int i = lastDevice; i < devices.size(); i++) {
               if (devices.get(i).isEmpty()) {
                    isEmptyAnyone = true;                    //Если нашли свободный, меняем флаг и
                    lastDevice = devices.get(i).getNumber(); //Запоменаем его номер
                    break;
               }
          }

          //А потом до проверенного в прошлый раз прибора, если не нашли свободный в предыдущем цикле
          if (!isEmptyAnyone) {
               for (int i = 0; i < lastDevice; i++) {
                    if (devices.get(i).isEmpty()) {
                         isEmptyAnyone = true;                     //Если нашли свободный меняем флаг и
                         lastDevice = devices.get(i).getNumber(); //Запоменаем его номер
                    }
               }
          }


          //Если нашли свободный прибор, то кидаем туда выбранную из буфера заявку, если свободных приборов нет, то ничего не делаем
          if (isEmptyAnyone) {
               //Проверяем есть ли у нас заявки в буферах, если есть, то кидаем их на приборы, если нет, то ничего не делаем
               boolean isBuffersEmpty = true;  //Если все буферы пустые
               for (Buffer buffer : buffers) {
                    if (!buffer.isEmpty())
                         isBuffersEmpty = false;
               }

               if (!isBuffersEmpty) {
                    Source.Request req = null;
                    int numberSourse = Main.countSources + 1; //Самый большой номер источника (самый низкий приоритет)
                    int numberRequest = Main.countRequests;   //Максимальный номер заявки

                    //Находим заявку от источника с мамым высоким приоритетом, если таких заявок несколько, то выбираем
                    //самую раннюю (наименьший номер) и запоминаем ссылку на неё
                    for (Buffer buffer : buffers) {
                         if (buffer.getNumberSource() < numberSourse && buffer.getNumberSource() != 0)
                              numberSourse = buffer.getNumberSource();
                    }

                    int numberBuffer = 0; //индекс буфера из которого забрали заявку (далее сдвигаем все заявки влево)
                    for (int i = 0; i < buffers.size(); i++) {
                         if (buffers.get(i).getNumberSource() == numberSourse && buffers.get(i).getRequest().getNumber() < numberRequest
                                 && buffers.get(i).getRequest().getNumber() != 0) {
                              numberRequest = buffers.get(i).getRequest().getNumber();
                              req = buffers.get(i).getRequest();
                              numberBuffer = i;
                         }
                    }

                    buffers.get(numberBuffer).delete(); //удаляем найденую наявку из буфера
                    //Добавляем информацию о нахождении заявки из нужного источника
                    sources.get(numberSourse - 1).settBP(buffers.get(numberBuffer).gettForSource());

                    Main.systemTime = buffers.get(numberBuffer).getTimeOut(); //Фисксируем время удаления

                    //Если нашли заявку, т.е. буферы НЕ пустые, то отправляем в свободный прибор на обработку
                    if (req != null) {
                         devices.get(lastDevice - 1).add(req, numberSourse);
                         Main.systemTime = devices.get(lastDevice - 1).getTimeAdd(); //Фиксируем время поступления заявки на прибор
                    }

                    //Сдвигаем все остальные буферы влево, последний будет копией предполседнего
                    for (int i = numberBuffer; i < buffers.size() - 1; i++) {
                         buffers.set(i, buffers.get(i + 1));
                    }
                    //очищаем последний, чтобы он стал пустым и устанавливаем его номер
                    buffers.get(Main.countBuffers - 1).clear();
                    buffers.get(Main.countBuffers - 1).setNumber(Main.countBuffers);
               }
          }

          //Проверяем есть ли прибор, который закончит обработку заявки раньше,
          //чем сгенерировалась самая ранняя из заявок, находящихся в листе
          Device device = devices.get(0);
          for(Device d : devices){
               if(!d.isEmpty()) {
                    device = d;
                    break;
               }
          }

          double minTr = device.getTimeToTreatment();
          int numberDevice = device.getNumber();
          for (Device d : devices) {
               if (d.getTimeToTreatment() < minTr && !d.isEmpty()) {
                    minTr = d.getTimeToTreatment();
                    numberDevice = d.getNumber();
               }
          }

          double minGr = requestList.get(0).gettGiner();
          for (Source.Request req : requestList) {
               if (req.gettGiner() < minGr)
                    minGr = req.gettGiner();
          }


          //Если нашли такой прибор, то фиксируем системное время и удаляем заявку из прибора
          if (minTr < minGr) {
               Main.systemTime = minTr;  //Фиксируем событие - Завершение обработки и удаление заявки из прибора.
               int numberSource = device.getNumberSource();
               devices.get(numberDevice - 1).delete();
               sources.get(numberSource - 1).settObc(devices.get(numberDevice - 1).gettForSource());
          }

          if (Main.generateIsReady) {
               boolean isAllDevicesEmpty = false;
               int count = 0;
               for (Device d : devices) {
                    if (d.isEmpty())
                         count++;
               }
               if (count == devices.size())
                    isAllDevicesEmpty = true;

               if(!isAllDevicesEmpty) {
                    Main.systemTime = minTr;      //Фиксируем событие - Завершение обработки и удаление заявки из прибора.
                    int numberSource = device.getNumberSource();
                    devices.get(numberDevice - 1).delete();
                    sources.get(numberSource - 1).settObc(devices.get(numberDevice - 1).gettForSource());
               }
          }
     }
}
