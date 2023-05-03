# About
 리틀팍스 앱에서 사용하는 기능으로, 사용자의 행동 패턴을 로그로 저장하여<br>
 고객의 불편함 및 버그 이슈를 해결하기 위해 만들어졌습니다.

이 라이브러리는 사용자 행동 패턴을 추적하고, 해당 정보를 로그로 기록하여<br>
개발자가 고객의 문제 발생한 시간대의 상황에 대한 로그를 모니터링<br>할 수 있도록 합니다.

또한, 로그를 분석하여 사용자가 직면한 문제점을 파악하고,<br>
이를 바탕으로 문제를 해결하기 위한 적절한 대응 방안을 마련<br>할 수 있도록 도와줍니다.

감사합니다!

# Getting Started
안드로이드 스튜디오나 gradle을 사용하시는 분께서는<br>
아래 dependencies를 build.gradle에 추가해주시면 바로 사용해보실수 있습니다.
```groovy
implementation 'com.github.only2433:LittlefoxLogMoniter:1.0.14'
```

# Usage
### Init
```Java
Log.init(this, Common.LOG_FILE, MonitorMode.RELEASE_MODE);
```
>사용할 Context, 저장할 파일 이름, 모니터링 모드를 설정한다.<br>
모니터링 모드는 DEBUG_MODE, RELEASE_MODE가 있다, <br>

>**DEBUG_MODE**는 **외부 폴더에 파일을 저장**하고, **RELEASE_MODE**는 **앱 내부에 저장**한다.<br>
앱을 배포시에는 **반드시 RELEASE_MODE**를 사용한다.

### Delete
```Java
   private void settingLogFile() {
        long logfileSize = Log.getLogfileSize();
        Log.f("Log file Size : " + logfileSize);
        if (logfileSize > Common.MAXIMUM_LOG_FILE_SIZE || logfileSize == 0L)
        {
            Log.initWithDeleteFile(mContext, Common.LOG_FILE, MonitorMode.RELEASE_MODE);
        }
    }
```
> 로그파일 용량이 10MB를 넘어가면 파일을 삭제 후<br>
재 생성하여, 파일 용량을 최대 10MB를 유지 하도록 한다.

### 사용 예시 - Save
```Java
    private void checkAnswerMode()
    {
        int locationPosition = 0;
        ReadingQuizExampleItemResult data = null;

        _AllClearButton.setVisibility(View.GONE);
        _OneClearButton.setVisibility(View.GONE);
        _CorrectCheckImage.setVisibility(View.VISIBLE);
        if (mQuizUserStudyData.isQuizCorrect())
        {
            _CorrectCheckImage.setImageResource(R.drawable.big_correct_icon);
        }
        else
        {
            _CorrectCheckImage.setImageResource(R.drawable.big_incorrect_icon);
        }


        for(int i = 0; i < mQuizResultData.getExampleList().size(); i++)
        {
            Log.f("ANSWER position : " + i +", answer sequence: " + mQuizUserStudyData.getCorrectAnswerSequenceList().get(i).getSequenceNumber()
                    +", text : " + mQuizUserStudyData.getCorrectAnswerSequenceList().get(i).getExampleText());
        }
        Log.f("\n");
        for(int i = 0; i < mQuizResultData.getExampleList().size(); i++)
        {
            Log.f("USER position : " + i +", answer sequence : " + mQuizUserStudyData.getUserSelectAnswerSequenceList().get(i).getSequenceNumber()
                    +", text : " + mQuizUserStudyData.getUserSelectAnswerSequenceList().get(i).getExampleText());
        }
```
> 위에 언급한 코드는 사용자가 퀴즈를 풀때, 정답과 사용자가 선택한 값을 로그에 저장하는 부분 이다.<br>
몇몇 고객이 1번 문제를 풀때, 정답이 A인데, B를 선택 했는데 를 선택했다고 우기면서 컴플레인 할때 가 있어<br>
정확하게 알려주기 위해 사용하는 코드 이다.

### 사용 예시 - 파일 로그 내용
```
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:314)	<I>ANSWER position : 0, answer sequence: 2, text : Mr. Freaky screamed and darted behind a stage curtain.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:314)	<I>ANSWER position : 1, answer sequence: 1, text : The pirate skeleton knocked over two buckets with his sword.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:314)	<I>ANSWER position : 2, answer sequence: 3, text : Water gushed from the ceiling and Pedro ducked.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:314)	<I>ANSWER position : 3, answer sequence: 5, text : Mog thought he had found the perfect career.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:314)	<I>ANSWER position : 4, answer sequence: 4, text : Winnie told Ben about what she had heard before the assembly.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:314)	<I>ANSWER position : 5, answer sequence: 6, text : The students were impressed with Pedro's treasure.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:317)	<I>

0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:320)	<I>USER position : 0, answer sequence : 2, text : Mr. Freaky screamed and darted behind a stage curtain.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:320)	<I>USER position : 1, answer sequence : 1, text : The pirate skeleton knocked over two buckets with his sword.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:320)	<I>USER position : 2, answer sequence : 3, text : Water gushed from the ceiling and Pedro ducked.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:320)	<I>USER position : 3, answer sequence : 6, text : Mog thought he had found the perfect career.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:320)	<I>USER position : 4, answer sequence : 4, text : Winnie told Ben about what she had heard before the assembly.
0419 14:46:19	ReadingSequenceQuizFragment(checkAnswerMode:320)	<I>USER position : 5, answer sequence : 5, text : The students were impressed with Pedro's treasure.
```

# License
본 프로젝트는 MIT 라이선스를 따릅니다.<br>
자세한 내용은 [LICENSE](https://github.com/only2433/LittlefoxLogMoniter/blob/master/LICENSE.md) 파일을 참고해주세요

