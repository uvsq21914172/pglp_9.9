package fr.uvsq21914172.pglp_99.commands;

public class CommandInput {
  
  public static Command commandParser(String command) {
    String[] elements = command.split("( )+");
    //Move,Add,Create,Remove,Edit,New,Load,Save,Quit
    //Move -n ${name} -x ${x} -y ${y}
    //Add -s ${shape} -g ${group}
    //Create -t ${type} -n ${name} ... [-x ${x} -y ${y}]
    //                     circle -r ${rayon}
    //                     square -w ${width}
    //                     rectangle -w ${width} -l ${length}
    //                     triangle -1 ${side1} -2 ${side2} -3 ${side3}
  }
  
}
