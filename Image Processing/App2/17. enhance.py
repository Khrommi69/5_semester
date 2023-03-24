from email.mime import image
from tkinter import *
from tkinter import filedialog as fd
from tkinter import messagebox as mb
from tkinter.ttk import Notebook
from PIL import Image, ImageTk, ImageOps, ImageFilter, ImageEnhance
import os
from matplotlib import pyplot as plt
import pyperclip
import json
import numpy as np
import cv2 as cv
import shutil
from enhance_slider_window import EnhanceSliderWindow
import pylab
from skimage import data
from skimage.filters import threshold_otsu

CONFIG_FILE = "config.json"


class PyPhotoEditor:
    def __init__(self):
        self.root = Tk()
        self.image_tabs = Notebook(self.root)
        self.opened_images = []

        self.selection_top_x = 0
        self.selection_top_y = 0
        self.selection_bottom_x = 0
        self.selection_bottom_y = 0

        self.canvas_for_selection = None
        self.selection_rect = None
        self.init()

    def init(self):
        self.root.title("Py Photo Editor")
        self.image_tabs.enable_traversal()
        self.root.bind("<Escape>", self._close)
        self.root.protocol("WM_DELETE_WINDOW", self._close)

        if not os.path.exists(CONFIG_FILE):
            with open(CONFIG_FILE, 'w') as f:
                json.dump({"opened_images": []}, f)
        else:
            self.load_images_from_config()

    def run(self):
        self.draw_menu()
        self.draw_widgets()

        self.root.mainloop()

    def draw_menu(self):
        menu_bar = Menu(self.root)

        file_menu = Menu(menu_bar, tearoff=0)
        file_menu.add_command(label="Открыть", command=self.open_new_images)
        file_menu.add_command(label="Сохранить", command=self.save_current_image)
        file_menu.add_command(label="Сохранить как", command=self.save_image_as)
        file_menu.add_command(label="Сохранить все", command=self.save_all_changes)
        file_menu.add_separator()
        file_menu.add_command(label="Закрыть изображение", command=self.close_current_image)
        file_menu.add_separator()
        file_menu.add_command(label="Удалить изображение", command=self.delete_current_image)
        file_menu.add_command(label="Переместить изображение", command=self.move_current_image)
        file_menu.add_separator()

        clipboard_menu = Menu(file_menu, tearoff=0)
        clipboard_menu.add_command(label="Добавить имя изображения в буфер обмена", command=self.name_to_clipboard)
        clipboard_menu.add_command(label="Добавить каталог изображений в буфер обмена", command=self.directory_to_clipboard)
        clipboard_menu.add_command(label="Добавить путь к изображению в буфер обмена", command=self.path_to_clipboard)
        file_menu.add_cascade(label="Буфер обмена", menu=clipboard_menu)
        
        file_menu.add_separator()
        file_menu.add_command(label="Выход", command=self._close)
        menu_bar.add_cascade(label="Файл", menu=file_menu)

        edit_menu = Menu(menu_bar, tearoff=0)
        transform_menu = Menu(edit_menu, tearoff=0)

        rotate_menu = Menu(transform_menu, tearoff=0)
        rotate_menu.add_command(label="Поворот влево на 90 градусов", command=lambda: self.rotate_current_image(90))
        rotate_menu.add_command(label="Поворот вправо на 90 градусов", command=lambda: self.rotate_current_image(-90))
        rotate_menu.add_command(label="Поворот влево на 180 градусов", command=lambda: self.rotate_current_image(180))
        rotate_menu.add_command(label="Поворот вправо на 180 градусов", command=lambda: self.rotate_current_image(-180))
        transform_menu.add_cascade(label="Поворот", menu=rotate_menu)

        flip_menu = Menu(edit_menu, tearoff=0)
        flip_menu.add_command(label="Отразить по горизонтали", command=lambda: self.flip_current_image("horizontally"))
        flip_menu.add_command(label="Отразить по вертикали", command=lambda: self.flip_current_image("vertically"))

        resize_menu = Menu(edit_menu, tearoff=0)
        resize_menu.add_command(label="25% начального размера", command=lambda: self.resize_current_image(25))
        resize_menu.add_command(label="50% начального размера", command=lambda: self.resize_current_image(50))
        resize_menu.add_command(label="75% начального размера", command=lambda: self.resize_current_image(75))
        resize_menu.add_command(label="125% начального размера", command=lambda: self.resize_current_image(125))
        resize_menu.add_command(label="150% начального размера", command=lambda: self.resize_current_image(150))
        resize_menu.add_command(label="200% начального размера", command=lambda: self.resize_current_image(200))

        filter_menu = Menu(edit_menu, tearoff=0)
        #filter_menu.add_command(label="Блюр", command=lambda: self.apply_filter_to_current_image(ImageFilter.BLUR))
        #filter_menu.add_command(label="Резкость", command=lambda: self.apply_filter_to_current_image(ImageFilter.SHARPEN))
        filter_menu.add_command(label="Контур1", command=lambda: self.apply_filter_to_current_image(ImageFilter.CONTOUR))
        filter_menu.add_command(label="Контур2", command=lambda: self.apply_filter_to_current_image(ImageFilter.FIND_EDGES))
        filter_menu.add_command(label="Контур3", command=lambda: self.apply_filter_to_current_image(ImageFilter.EMBOSS))
        #filter_menu.add_command(label="Детализация", command=lambda: self.apply_filter_to_current_image(ImageFilter.DETAIL))
        filter_menu.add_command(label="Плавность", command=lambda: self.apply_filter_to_current_image(ImageFilter.SMOOTH))
        filter_menu.add_command(label="Медианный фильтр", command=lambda: self.apply_filter_to_current_image(ImageFilter.MedianFilter))
        filter_menu.add_command(label="Линейный  фильтр", command=lambda: self.apply_filter_to_current_image(ImageFilter.ModeFilter(size=3)))
        #filter_menu.add_command(label="Бинаризация", command=lambda: self.binarization())
        #filter_menu.add_command(label="Бинаризация2", command=lambda: self.bin_im())
        filter_menu.add_command(label="Негатив", command=lambda: self.negative())
        filter_menu.add_command(label="Шум", command=lambda: self.salt_and_pepper())
        #filter_menu.add_command(label="temp", command=lambda: self.apply_filter_to_current_image(ImageFilter.))

        crop_menu = Menu(edit_menu, tearoff=0)
        crop_menu.add_command(label="Начать выборку", command=self.start_area_selection_of_current_image)
        crop_menu.add_command(label="Закончить выборку", command=self.stop_area_selection_of_current_image)

        convert_menu = Menu(edit_menu, tearoff=0)
        convert_menu.add_command(label="Черно-белый", command=lambda: self.convert_current_image("1"))
        convert_menu.add_command(label="Оттенки серого", command=lambda: self.convert_current_image("L"))
        convert_menu.add_command(label="RGB", command=lambda: self.convert_current_image("RGB"))
        #convert_menu.add_command(label="RGBA", command=lambda: self.convert_current_image("RGBA"))
        #convert_menu.add_command(label="CMYK", command=lambda: self.convert_current_image("CMYK"))
        #convert_menu.add_command(label="LAB", command=lambda: self.convert_current_image("LAB"))
        #convert_menu.add_command(label="HSV", command=lambda: self.convert_current_image("HSV"))
        #convert_menu.add_command(label="Свернуть цвета RGB", command=lambda: self.convert_current_image("roll"))
        convert_menu.add_command(label="Красный", command=lambda: self.convert_current_image("R"))
        convert_menu.add_command(label="Зеленый", command=lambda: self.convert_current_image("G"))
        convert_menu.add_command(label="Синий", command=lambda: self.convert_current_image("B"))

        #convert_menu.add_command(label="Формат цвета", command=lambda: self.hists())

        enhance_menu = Menu(edit_menu, tearoff=0)
        enhance_menu.add_command(label="Цвет", command=lambda: self.enhance_current_image("Color", ImageEnhance.Color))
        enhance_menu.add_command(label="Контраст", command=lambda: self.enhance_current_image("Contrast", ImageEnhance.Contrast))
        enhance_menu.add_command(label="Яркость", command=lambda: self.enhance_current_image("Brightness", ImageEnhance.Brightness))
        #enhance_menu.add_command(label="Острота", command=lambda: self.enhance_current_image("Sharpness", ImageEnhance.Sharpness))

        hists_menu = Menu(edit_menu, tearoff=0)
        hists_menu.add_command(label="Открыть диаграммы", command=lambda: self.hists_rgb())
        #hists_menu.add_command(label="Диаграмма яркости", command=lambda: self.hists_bright())


        #edit_menu.add_cascade(label="Трансформировать", menu=transform_menu)
        #edit_menu.add_cascade(label="Поворот", menu=flip_menu)
        edit_menu.add_cascade(label="Изменение размера", menu=resize_menu)
        edit_menu.add_cascade(label="Фильтры", menu=filter_menu)
        #edit_menu.add_cascade(label="Обрезка", menu=crop_menu)
        edit_menu.add_cascade(label="Конвертация", menu=convert_menu)
        edit_menu.add_cascade(label="Эффекты", menu=enhance_menu)
        edit_menu.add_cascade(label="Диаграммы", menu=hists_menu)

        menu_bar.add_cascade(label="Редактировать", menu=edit_menu)

        self.root.configure(menu=menu_bar)

    def draw_widgets(self):
        self.image_tabs.pack(fill="both", expand=1)

    def load_images_from_config(self):
        with open(CONFIG_FILE, 'r') as f:
            config = json.load(f)
        
        paths = config["opened_images"]
        for path in paths:
            self.add_new_image(path)

    def open_new_images(self):
        image_paths = fd.askopenfilenames(filetypes=(("Images", "*.jpeg;*.jpg;*.png"), ))
        for image_path in image_paths:
            self.add_new_image(image_path)

    def add_new_image(self, image_path):
        image = Image.open(image_path)
        image_tk = ImageTk.PhotoImage(image)
        self.opened_images.append([image_path, image])

        image_tab = Frame(self.image_tabs)

        image_panel = Canvas(image_tab, width=image_tk.width(), height=image_tk.height(), bd=0, highlightthickness=0)
        image_panel.image = image_tk
        image_panel.create_image(0, 0, image=image_tk, anchor="nw")
        image_panel.pack(expand="yes")

        self.image_tabs.add(image_tab, text=os.path.split(image_path)[1])
        self.image_tabs.select(image_tab)

    def get_current_working_data(self):
        """returns current (tab, image, path)
        """
        current_tab = self.image_tabs.select()
        if not current_tab:
            return None, None, None
        tab_number = self.image_tabs.index(current_tab)
        path, image = self.opened_images[tab_number]

        return current_tab, path, image

    def save_current_image(self):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        tab_number = self.image_tabs.index(current_tab)
        
        if path[-1] == '*':
            path = path[:-1]
            self.opened_images[tab_number][0] = path
            image.save(path)
            self.image_tabs.add(current_tab, text=os.path.split(path)[1])

    def save_image_as(self):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        tab_number = self.image_tabs.index(current_tab)

        old_path, old_ext = os.path.splitext(path)
        if '*' in old_ext:
            old_ext = old_ext[:-1]
        
        new_path = fd.asksaveasfilename(initialdir=old_path, filetypes=(("Images", "*.jpeg;*.jpg;*.png"), ))
        if not new_path:
            return
        
        new_path, new_ext = os.path.splitext(new_path)
        if not new_ext:
            new_ext = old_ext
        elif old_ext != new_ext:
            mb.showerror("Некорректное расширение", f"Получено неверное расширение: {new_ext}. Старое расширение: {old_ext}")
            return
        
        image.save(new_path + new_ext)
        image.close()

        del self.opened_images[tab_number]
        self.image_tabs.forget(current_tab)

        self.add_new_image(new_path + new_ext)

    def save_all_changes(self):
        for index, (path, image) in enumerate(self.opened_images):
            if path[-1] != '*':
                continue
            path = path[:-1]
            self.opened_images[index][0] = path
            image.save(path)
            self.image_tabs.tab(index, text=os.path.split(path)[1])

    def close_current_image(self):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        index = self.image_tabs.index(current_tab)

        image.close()
        del self.opened_images[index]
        self.image_tabs.forget(current_tab)

    def delete_current_image(self):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        index = self.image_tabs.index(current_tab)

        if not mb.askokcancel("Удалить изображение", "Вы уверенв, что хотите удалить изображение?\nЭта операция не отменяется!"):
            return

        image.close()
        os.remove(path)

        del self.opened_images[index]
        self.image_tabs.forget(current_tab)

    def move_current_image(self):
        current_tab, old_path, image = self.get_current_working_data()
        if not current_tab:
            return
        index = self.image_tabs.index(current_tab)

        new_dir = fd.askdirectory(initialdir=os.path.dirname(old_path))
        if not new_dir:
            return
        
        new_path = os.path.join(new_dir, os.path.split(old_path)[1])

        image.close()
        shutil.move(old_path, new_path)

        del self.opened_images[index]
        self.image_tabs.forget(current_tab)

        self.add_new_image(new_path)

    def update_image_inside_app(self, current_tab, image):
        tab_number = self.image_tabs.index(current_tab)
        tab_frame = self.image_tabs.children[current_tab[current_tab.rfind('!'):]]
        canvas = tab_frame.children['!canvas']

        self.opened_images[tab_number][1] = image

        image_tk = ImageTk.PhotoImage(image)

        canvas.delete("all")
        canvas.image = image_tk
        canvas.configure(width=image_tk.width(), height=image_tk.height())
        canvas.create_image(0, 0, image=image_tk, anchor="nw")

        image_path = self.opened_images[tab_number][0]
        if image_path[-1] != '*':
            image_path += '*'
            self.opened_images[tab_number][0] = image_path
            image_name = os.path.split(image_path)[1]
            self.image_tabs.tab(current_tab, text=image_name)

    def rotate_current_image(self, degrees):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        
        image = image.rotate(degrees)
        self.update_image_inside_app(current_tab, image)

    def flip_current_image(self, flip_type):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        
        if flip_type == "horizontally":
            image = ImageOps.mirror(image)
        elif flip_type == "vertically":
            image = ImageOps.flip(image)

        self.update_image_inside_app(current_tab, image)

    def resize_current_image(self, percents):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return

        w, h = image.size
        w = (w * percents) // 100
        h = (h * percents) // 100

        image = image.resize((w, h), Image.ANTIALIAS)
        self.update_image_inside_app(current_tab, image)

    def apply_filter_to_current_image(self, filter_type):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        
        image = image.filter(filter_type)
        self.update_image_inside_app(current_tab, image)

    def start_area_selection_of_current_image(self):
        current_tab = self.image_tabs.select()
        if not current_tab:
            return
        tab_frame = self.image_tabs.children[current_tab[current_tab.rfind('!'):]]
        canvas = tab_frame.children['!canvas']

        self.canvas_for_selection = canvas
        self.selection_rect = canvas.create_rectangle(
            self.selection_top_x, self.selection_top_y, 
            self.selection_bottom_x, self.selection_bottom_y, 
            dash=(10, 10), fill='', outline="white", width=2    
        )

        canvas.bind("<Button-1>", self.get_selection_start_pos)
        canvas.bind("<B1-Motion>", self.update_selection_end_pos)

    def get_selection_start_pos(self, event):
        self.selection_top_x, self.selection_top_y = event.x, event.y

    def update_selection_end_pos(self, event):
        self.selection_bottom_x, self.selection_bottom_y = event.x, event.y
        if self.canvas_for_selection is not None and self.selection_rect is not None:
            self.canvas_for_selection.coords(
                self.selection_rect, 
                self.selection_top_x, self.selection_top_y,
                self.selection_bottom_x, self.selection_bottom_y
            )

    def stop_area_selection_of_current_image(self):
        self.canvas_for_selection.unbind("<Button-1>")
        self.canvas_for_selection.unbind("<B1-Motion>")

        self.canvas_for_selection.delete(self.selection_rect)

        self.crop_current_image()

        self.selection_rect = None
        self.canvas_for_selection = None
        self.selection_top_x, self.selection_top_y = 0, 0
        self.selection_bottom_x, self.selection_bottom_y = 0, 0

    def crop_current_image(self):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        
        image = image.crop((
            self.selection_top_x, self.selection_top_y,
            self.selection_bottom_x, self.selection_bottom_y
        ))

        self.update_image_inside_app(current_tab, image)

    def convert_current_image(self, mode):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return

        if mode == "roll":
            if image.mode != "RGB":
                mb.showerror("Ошибка свертки RGB", f"Нельзя сворачивать изображение другого формата '{image.mode}'")
                return
            
            image = Image.fromarray(np.array(image)[:,:,::-1])
            self.update_image_inside_app(current_tab, image)
            return
        
        elif mode in "R G B".split(' '):
            if image.mode != "RGB":
                mb.showerror("Ошибка свертки RGB", f"Не удается разделить канал изображения в режиме, отличном от RGB. '{image.mode}'")
                return

            a = np.array(image)
            a[:,:,(mode!="R", mode!="G", mode!="B")] *= 0
            image = Image.fromarray(a)
            self.update_image_inside_app(current_tab, image)
            return
        
        try:
            image = image.convert(mode)
            self.update_image_inside_app(current_tab, image)
        except ValueError as e:
            mb.showerror("Ошибка преобразоания", f"Ошибка преобразования: '{e}'")

    def enhance_current_image(self, name, enhance):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        
        EnhanceSliderWindow(self.root, name, enhance, image, current_tab, self.update_image_inside_app)

    def name_to_clipboard(self):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        
        name = os.path.split(path)[1]
        pyperclip.copy(name)

        mb.showinfo("Буфер обмена", f"Имя '{name}' добавлен в буфер!")

    def directory_to_clipboard(self):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        
        directory = os.path.split(path)[0]
        pyperclip.copy(directory)

        mb.showinfo("Буфер обмена", f"Папка '{directory}' добавлена в буфер!")

    def path_to_clipboard(self):
        current_tab, path, image = self.get_current_working_data()
        if not current_tab:
            return
        
        pyperclip.copy(path)

        mb.showinfo("Буфер обмена", f"Путь '{path}' добавлен в буфер!")

    def save_images_to_config(self):
        paths = [(path[:-1] if path[-1] == '*' else path) for (path, image) in self.opened_images]
        images = {"opened_images": paths}
        with open(CONFIG_FILE, 'w') as f:
            json.dump(images, f, indent=4)

    def unsaved_images(self):
        for path, _ in self.opened_images:
            if path[-1] == "*":
                return True
        return False

    def _close(self, event=None):
        if self.unsaved_images():
            if not mb.askyesno("Несохраненные изменения", "Есть несохраненнве изменения! Выйти?"):
                return

        self.save_images_to_config()
        self.root.quit()
    
    def hists_rgb(self):
        plt.clf()
        current_tab, path, image = self.get_current_working_data()
        photo = cv.imread(path)
        #cv.imshow('photo', photo)
        color = ('b', 'g', 'r')
        
        for i, color in enumerate (color):
            hist = cv.calcHist([photo], [i], None, [256], [0, 256])
            pylab.subplot(2, 2, i+1)
            plt.plot(hist, color = color)
            plt.xlim([0, 260])
        pylab.subplot(2, 2, 4)
        plt.hist(photo.ravel(), 256, [0, 256])
        plt.show()
        cv.waitKey(0)
        cv.destroyAllWindows()
    
    
    def negative(self):
        current_tab, path, image = self.get_current_working_data()
        img = Image.open(path)
        for i in range(0, image.size[0] - 1):
            for j in range(0, image.size[1] - 1):
                pixelColorVals = image.getpixel((i, j))

                redPixel = 255 - pixelColorVals[0]
                greenPixel = 255 - pixelColorVals[1]
                bluePixel = 255 - pixelColorVals[2]
                img.putpixel((i, j), (redPixel, greenPixel, bluePixel))
        #img.show()
        if not current_tab:
            return
        
        #image = image.filter(filter_type)
        self.update_image_inside_app(current_tab, img)
    def binarization(self):
        current_tab, path, image = self.get_current_working_data()
        img = Image.open(path)
        thresh = 200
        width, height = image.size
        for x in range(width):
            for y in range(height):
                if image.getpixel((x, y)) < thresh:
                    img.putpixel((x, y), 0)
                else:
                    img.putpixel((x, y), 255)
        if not current_tab:
            return
        self.update_image_inside_app(current_tab, img)

    def salt_and_pepper(self):
        prob=0.05
        current_tab, path, image = self.get_current_working_data()
        img = Image.open(path)
        #if prob <= 0:
            #self.update_image_inside_app(current_tab, img)

        arr = np.asarray(img)
        original_dtype = arr.dtype
        intensity_levels = 2 ** (arr[0, 0].nbytes * 8)

        min_intensity = 0
        max_intensity = intensity_levels - 1
        random_image_arr = np.random.choice(
            [min_intensity, 1, np.nan], p=[prob / 2, 1 - prob, prob / 2], size=arr.shape
        )
        salt_and_peppered_arr = arr.astype(np.float) * random_image_arr
        salt_and_peppered_arr = np.nan_to_num(
            salt_and_peppered_arr, nan=max_intensity
        ).astype(original_dtype)

        if not current_tab:
            return
        
        #image = image.filter(filter_type)
        self.update_image_inside_app(current_tab, Image.fromarray(salt_and_peppered_arr))
    """
    def bin_im(self):
        # Load image
        current_tab, path, image = self.get_current_working_data()
        img = Image.open(path)
        thresh = threshold_otsu(img)
        binary = img > thresh
        palette = [  
            255, 0, 255,  # magenta
            255, 255, 0   # yellow
        ]
        palette += (768-len(palette))*[0]
        p = Image.fromarray((binary*1).astype(np.uint8))
        p.putpalette(palette)
        if not current_tab:
            return
        self.update_image_inside_app(current_tab, p)
    """
    def bin_im(self):
        factor = 50
        color_1 = ()
        color2 = ()
        current_tab, path, image = self.get_current_working_data()
        img = Image.open(path)
        width, height = img.size
        for i in range(width):
            for j in range(height):
                a = img[i, j][0]
                b = img[i, j][1]
                c = img[i, j][2]
                S = a + b + c
                if (S > factor):
                    a, b, c = 255, 255, 255
                else:
                    a, b, c = 0, 0, 0
                img.point(a, b, c)
        if not current_tab:
            return
        self.update_image_inside_app(current_tab, img)


    

if __name__ == "__main__":
    PyPhotoEditor().run()

