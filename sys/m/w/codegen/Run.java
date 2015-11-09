package m.w.codegen;

import m.w.frs.mg.domain.InfoType;

public class Run {
    public static void main(String[] args) {
        CodeGen.gen(InfoType.class);
    }
}
