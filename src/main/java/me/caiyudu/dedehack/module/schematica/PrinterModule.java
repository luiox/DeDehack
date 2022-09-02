package me.caiyudu.dedehack.module.schematica;

import com.github.lunatrius.schematica.client.printer.SchematicPrinter;

import me.caiyudu.dedehack.module.Module;

public class PrinterModule extends Module
{

    public PrinterModule()
    {
        super("Printer", new String[] {"SchematicaPrinter"}, "Integration of Schematica's printer", "NONE", -1, ModuleType.SCHEMATICA);
    }
    
    @Override
    public void toggleNoSave()
    {
        
    }
    
    @Override
    public String getMetaData()
    {
        return SchematicPrinter.INSTANCE.IsStatcaiyuduy() ? "Statcaiyuduy" : "Printing";
    }
    
    @Override
    public void toggle()
    {
        super.toggle();
        SchematicPrinter.INSTANCE.setPrinting(this.isEnabled());
    }

}
