package com.youxiao.ui.activity.work.knowledgeevaluation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.youxiao.base.BaseActivity;
import com.youxiao.R;

/**
 * 知识考评
 */
public class KnowledgeEvaluationActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLinearLayout_Back;

    private ImageView mImageView_EvaluationPractice;
    private ImageView mImageView_EvaluationRanklist;
    private ImageView mImageView_BusinessEvaluation;
    private ImageView mImageView_FileEvaluation;
    private ImageView mImageView_QuestionBankEdit;
    private ImageView mImageView_AskAnswer;

    private enum ItemType {
        EVALUATION_PRACTICE, EVALUATION_RANKLIST, BUSINESS_EVALUATION, FILE_EVALUATION, QUESTION_BACK_EDIT, ASK_ANSWER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_evaluation);

        super.init();
    }

    @Override
    public void initView() {
        mLinearLayout_Back = (LinearLayout) findViewById(R.id.id_ll_back);
        mImageView_EvaluationPractice = (ImageView) findViewById(R.id.id_iv_evaluation_practice);
        mImageView_EvaluationRanklist = (ImageView) findViewById(R.id.id_iv_evaluation_ranklist);
        mImageView_BusinessEvaluation = (ImageView) findViewById(R.id.id_iv_business_evaluation);
        mImageView_FileEvaluation = (ImageView) findViewById(R.id.id_iv_file_evaluation);
        mImageView_QuestionBankEdit = (ImageView) findViewById(R.id.id_iv_question_bank_edit);
        mImageView_AskAnswer = (ImageView) findViewById(R.id.id_iv_ask_answer);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mLinearLayout_Back.setOnClickListener(this);

        mImageView_EvaluationPractice.setOnClickListener(this);
        mImageView_EvaluationRanklist.setOnClickListener(this);
        mImageView_BusinessEvaluation.setOnClickListener(this);
        mImageView_FileEvaluation.setOnClickListener(this);
        mImageView_QuestionBankEdit.setOnClickListener(this);
        mImageView_AskAnswer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_iv_evaluation_practice:
                selectItem(ItemType.EVALUATION_PRACTICE);
                break;
            case R.id.id_iv_evaluation_ranklist:
                selectItem(ItemType.EVALUATION_RANKLIST);
                break;
            case R.id.id_iv_business_evaluation:
                selectItem(ItemType.BUSINESS_EVALUATION);
                break;
            case R.id.id_iv_file_evaluation:
                selectItem(ItemType.FILE_EVALUATION);
                break;
            case R.id.id_iv_question_bank_edit:
                selectItem(ItemType.QUESTION_BACK_EDIT);
                break;
            case R.id.id_iv_ask_answer:
                selectItem(ItemType.ASK_ANSWER);
                break;
            case R.id.id_ll_back:
                finish();
                break;
            default:
                break;
        }
        Intent intent = new Intent(this,AnswerActivity.class);
        startActivity(intent);
    }

    private void selectItem(ItemType itemType) {
        resetAllItem();
        switch (itemType) {
            case EVALUATION_PRACTICE:
                mImageView_EvaluationPractice.setImageResource(R.drawable.evaluation_practice_selected);
                break;
            case BUSINESS_EVALUATION:
                mImageView_BusinessEvaluation.setImageResource(R.drawable.business_evaluation_selected);
                break;
            case ASK_ANSWER:
                mImageView_AskAnswer.setImageResource(R.drawable.ask_answer_selected);
                break;
            case EVALUATION_RANKLIST:
                mImageView_EvaluationRanklist.setImageResource(R.drawable.evaluation_ranklist_selected);
                break;
            case FILE_EVALUATION:
                mImageView_FileEvaluation.setImageResource(R.drawable.file_evaluation_selected);
                break;
            case QUESTION_BACK_EDIT:
                mImageView_QuestionBankEdit.setImageResource(R.drawable.question_bank_edit_selected);
                break;
            default:
                break;
        }
    }

    private void resetAllItem() {
        mImageView_BusinessEvaluation.setImageResource(R.drawable.business_evaluation_normal);
        mImageView_AskAnswer.setImageResource(R.drawable.ask_answer_normal);
        mImageView_EvaluationPractice.setImageResource(R.drawable.evaluation_practice_normal);
        mImageView_FileEvaluation.setImageResource(R.drawable.file_evaluation_normal);
        mImageView_QuestionBankEdit.setImageResource(R.drawable.question_bank_edit_normal);
        mImageView_EvaluationRanklist.setImageResource(R.drawable.evaluation_ranklist_normal);
    }
}
