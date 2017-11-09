package com.codigo.aplios.toolbox.core;

interface IButton extends Cloneable {
	void setCaption(String text);

	String getCaption();
}

// class Button implements IButton
// {
// @Override
// public Object clone()
// {
// return Object.;
// }
//
// @Override
// public void setCaption(String text) {
//
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public String getCaption() {
//
// // TODO Auto-generated method stub
// return null;
// }
// }
//
// public interface IWindow extends Cloneable
// {
// int Height { get; set; }
// int Width { get; set; }
// }
//
// public class Window implements IWindow
// {
// public int Height { get; set; }
// public int Width { get; set; }
//
// public object Clone()
// {
// return this.MemberwiseClone();
// }
// }
//
// interface IUIFactory
// {
// IButton CreateButton();
// IWindow CreateWindow();
// }
//
// public abstract class UIFactory : IUIFactory
// {
// private IDictionary<Type, ICloneable> mPrototypes = new Dictionary<Type, ICloneable>();
//
// protected ICloneable GetPrototype(Type a_type)
// {
// if (mPrototypes.ContainsKey(a_type))
// {
// return mPrototypes[a_type];
// }
//
// return null;
// }
//
// protected void AddPrototype(Type a_type, ICloneable a_prototype)
// {
// mPrototypes[a_type] = a_prototype;
// }
//
// public abstract IButton CreateButton();
// public abstract IWindow CreateWindow();
// }
//
///// <summary>
///// Fabryka produkująca kontrolki dla środowiska Windows
///// </summary>
// public class WindowsUIFactory : UIFactory
// {
// public override IButton CreateButton()
// {
// IButton prototype = GetPrototype(typeof(IButton)) as IButton;
//
// if (prototype == null)
// {
// prototype = new Button();
// prototype.Caption = "Windows Button";
// AddPrototype(typeof(IButton), prototype);
// }
//
// return prototype.Clone() as IButton;
// }
//
// public override IWindow CreateWindow()
// {
// IWindow prototype = GetPrototype(typeof(IWindow)) as IWindow;
//
// if (prototype == null)
// {
// prototype = new Window();
// prototype.Height = 300;
// prototype.Width = 400;
// AddPrototype(typeof(IWindow), prototype);
// }
//
// return prototype.Clone() as IWindow;
// }
// }
//
///// <summary>
///// Fabryka produkująca kontrolki dla środowiska Gnome
///// </summary>
// public class GnomeUIFactory : UIFactory
// {
// //...
// }
