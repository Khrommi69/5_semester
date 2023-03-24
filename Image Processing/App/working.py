import tkinter as tk
from tkinter import filedialog
from PIL import Image, ImageTk

class ImageProcessor:
    def __init__(self, master):
        self.master = master
        master.title("Image Processor")

        # Создание окон для изображений
        self.original_image = tk.Canvas(master, width=400, height=400, bg="gray")
        self.original_image.grid(row=0, column=0, padx=5, pady=5)
        self.processed_image = tk.Canvas(master, width=400, height=400, bg="gray")
        self.processed_image.grid(row=0, column=1, padx=5, pady=5)

        # Создание верхнего меню
        menubar = tk.Menu(master)
        filemenu = tk.Menu(menubar, tearoff=0)
        filemenu.add_command(label="Open", command=self.open_image)
        filemenu.add_command(label="Save", command=self.save_image)
        menubar.add_cascade(label="File", menu=filemenu)
        master.config(menu=menubar)

        # Создание строки состояния
        self.statusbar = tk.Label(master, text="Ready", bd=1, relief=tk.SUNKEN, anchor=tk.W)
        self.statusbar.grid(row=1, column=0, columnspan=2, sticky=tk.W+tk.E)

        # Создание панели инструментов
        toolbar = tk.Frame(master)
        toolbar.grid(row=2, column=0, columnspan=2, sticky=tk.W)
        self.button_open = tk.Button(toolbar, text="Open", command=self.open_image)
        self.button_open.pack(side=tk.LEFT, padx=2, pady=2)
        self.button_save = tk.Button(toolbar, text="Save", command=self.save_image)
        self.button_save.pack(side=tk.LEFT, padx=2, pady=2)
        self.button_undo = tk.Button(toolbar, text="Undo", command=self.undo)
        self.button_undo.pack(side=tk.LEFT, padx=2, pady=2)
        self.button_copy = tk.Button(toolbar, text="Copy", command=self.copy_image)
        self.button_copy.pack(side=tk.LEFT, padx=2, pady=2)

    def open_image(self):
        filetypes = (("Bitmap files", "*.bmp"), ("JPEG files", "*.jpg;*.jpeg"))
        filepath = filedialog.askopenfilename(title="Open Image", filetypes=filetypes)
        if filepath:
            image = Image.open(filepath)
            self.original_image.image = ImageTk.PhotoImage(image)
            self.original_image.config(width=image.width, height=image.height)  # установка размеров холста
            self.original_image.create_image(0, 0, image=self.original_image.image, anchor=tk.NW)

    def save_image(self):
        pass

    def undo(self):
        pass

    def copy_image(self):
        pass


root = tk.Tk()
app = ImageProcessor(root)
root.mainloop()
