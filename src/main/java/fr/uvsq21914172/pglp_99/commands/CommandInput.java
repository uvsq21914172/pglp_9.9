package fr.uvsq21914172.pglp_99.commands;

import fr.uvsq21914172.pglp_99.engine.DrawingTui;

public class CommandInput {
  
  public static Command commandParser(String cmdstr, DrawingTui dw) {
    String[] elements = cmdstr.split("(\\s)+");
    Command command;
    //Move,Add,Create,Remove,Edit,New,Load,Save,Quit
    //Move -n ${name} -x ${x} -y ${y}
    //Add -s ${shape} -g ${group}
    //Create -n ${name} -t ${type} ... 
    //                      group
    //                      ***shape [-x ${x} -y ${y}]
    //                      circle -r ${rayon}
    //                      square -w ${width}
    //                      rectangle -w ${width} -l ${length}
    //                      triangle -1 ${side1} -2 ${side2} -3 ${side3}
    //Remove (-s ${shape} [-g ${group}]| -g ${group} -d)
    //Edit -s ${shape} ...
    //                  ***shape [-x ${x} -y ${y}]
    //                  circle [-r ${rayon}]
    //                  square [-w ${width}]
    //                  rectangle [-w ${width} -l ${length}]
    //                  triangle [-1 ${side1} -2 ${side2} -3 ${side3}]
    //Show [-gs | -g ${group}]
    //New
    //Load -n ${name}
    //Save -n ${name}
    //Quit
    if(elements[0].equalsIgnoreCase("Move")) {
      command = new Move(dw);
      
    }else if(elements[0].equalsIgnoreCase("Add")) {
      command = new Add(dw);
      
    }else if(elements[0].equalsIgnoreCase("Create")) {
      command = new Create(dw);
      
    }else if(elements[0].equalsIgnoreCase("Remove")) {
      command = new Remove(dw);
      
    }else if(elements[0].equalsIgnoreCase("Edit")) {
      command = new Edit(dw);
      
    }else if(elements[0].equalsIgnoreCase("New")) {
      command = new New(dw);
      
    }else if(elements[0].equalsIgnoreCase("Load")) {
      command = new Load(dw);
      
    }else if(elements[0].equalsIgnoreCase("Save")) {
      command = new Save(dw);
      
    }else if(elements[0].equalsIgnoreCase("Quit")) {
      command = new Quit(dw);
      
    }else if(elements[0].equalsIgnoreCase("Show")) {
      command = new Show(dw);
      
    }else {
      return null;
    }
    
    for(int i = 1; i < elements.length; i++) {
      if(elements[i].equalsIgnoreCase("-n")) {
        if(i+1 < elements.length) {
          command.addParam("name", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-t")) {
        if(i+1 < elements.length) {
          command.addParam("type", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-x")) {
        if(i+1 < elements.length) {
          command.addParam("x", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-y")) {
        if(i+1 < elements.length) {
          command.addParam("y", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-s")) {
        if(i+1 < elements.length) {
          command.addParam("shape", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-g")) {
        if(i+1 < elements.length) {
          command.addParam("group", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-r")) {
        if(i+1 < elements.length) {
          command.addParam("rayon", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-w")) {
        if(i+1 < elements.length) {
          command.addParam("width", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-l")) {
        if(i+1 < elements.length) {
          command.addParam("length", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-1")) {
        if(i+1 < elements.length) {
          command.addParam("side1", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-2")) {
        if(i+1 < elements.length) {
          command.addParam("side2", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-3")) {
        if(i+1 < elements.length) {
          command.addParam("side3", elements[i+1]);
        }
        i++;
      }else if(elements[i].equalsIgnoreCase("-d")) {
        command.addParam("delete", "true");
      }else if(elements[i].equalsIgnoreCase("-gs")) {
          command.addParam("groups", "true");
      }else if(elements[i].equalsIgnoreCase("-all")) {
        command.addParam("all", "true");
      }else{
        System.out.println("[DrawingApp] : flag\"" + elements[i] +"\" unrecognized");
        return null;
      }
    }
    
    return command;
  }
  
}
