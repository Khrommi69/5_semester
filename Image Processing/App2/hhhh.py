from matplotlib import pyplot as plt
import cv2 as cv

beach = cv.imread('resources/beach.jpg')
cv.imshow('beach', beach)
color = ('b', 'g', 'r')
for i, color in enumerate (color):
    hist = cv.calcHist([beach], [i], None, [256], [0, 256])
    plt.title('beach')
    plt.xlabel('Bins')
    plt.ylabel('num of perlex')
    plt.plot(hist, color = color)
    plt.xlim([0, 260])
    plt.show()
    cv.waitKey(0)
    cv.destroyAllWindows()